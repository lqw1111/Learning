package Concurrent.Character6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new AccountCAS(10000);
        Account.demo(account);
    }
}

class AccountCAS implements Account{

    private AtomicInteger balance;

    public AccountCAS(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        while(true) {
            //获取最近值
            int prev = balance.get();

            //修改
            int next = prev - amount;

            //同步到主存
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}


//可以使用synchronized来改进
class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void withdraw(Integer amount) {
        this.balance -= amount;
    }
}

interface Account {
    Integer getBalance();

    void withdraw(Integer amount);

    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0 ; i < 1000 ; i ++){
            ts.add(new Thread(() -> {
                account.withdraw(10);
            }));
        }

        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance() + " cost : " + (end - start) / 1000_00 + " ms");
    }
}