package Concurrent.Character8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MICROSECONDS, 1, (queue, task) -> {
            // 1）死等
//            queue.put(task);
            // 2）带超时等待
//            queue.offer(task, 10000, TimeUnit.MILLISECONDS);
            // 3）让调用者放弃任务执行
//            System.out.println("放弃任务" + task);
            // 4）让调用者跑出异常
//            throw new RuntimeException("任务执行失败" + task);
            // 5）让调用者自己执行任务
            task.run();

        });
        for (int i = 0 ; i < 3 ; i ++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + j);
            });
        }
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    //执行任务
    public void execute(Runnable task) {

        // 当任务数没有超过 coresize的时候，直接交给worker对象执行
        // 如果任务数超过coreSize时，加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                System.out.println("新增 worker " + worker + ", " + task);
                workers.add(worker);
                worker.start();
            } else {
                System.out.println("加入任务队列" + task);
//                taskQueue.put(task);
                // 1）死等
                // 2）带超时等待
                // 3）让调用者放弃任务执行
                // 4）让调用者跑出异常
                // 5）让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }


    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        public void run() {
            // 执行任务
            // 1) 当task不为空，执行任务
            // 2）当task执行完毕，再接着从任务队列获取任务并执行
//            while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    System.out.println("正在执行..." + task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                System.out.println("worker " + this + " 被移除");
                workers.remove(this);
            }
        }
    }
}

class BlockingQueue<T> {

    // 1、任务队列
    private Deque<T> queue = new ArrayDeque<>();

    // 2、锁
    private ReentrantLock lock = new ReentrantLock();

    // 3、生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4、消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5、容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try{
            //将timeout 统一转换为 纳秒
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try{
                    // 返回的是剩余的时间
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take(){
        lock.lock();
        try{
            while (queue.isEmpty()) {
                try{
                    emptyWaitSet.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    System.out.println("加入等待队列");
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //带超时时间的阻塞方法
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    System.out.println(nanos);
                    if (nanos <= 0) {
                        return false;
                    }
                    fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("加入任务队列");
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }


    //获取大小
    public int size(){
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //队列是否已满
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                System.out.println(Thread.currentThread().getName() + " 加入队列"  + task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}