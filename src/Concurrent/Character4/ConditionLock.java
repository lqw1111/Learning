package Concurrent.Character4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLock {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition condition2 = lock.newCondition();
        Condition condition1 = lock.newCondition();

        lock.lock();

        //进入休息室等待
        condition1.wait();

        condition1.signal();
        condition1.signalAll();
    }
}
