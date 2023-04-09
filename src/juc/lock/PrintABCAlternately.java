package juc.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// 三个线程交替打印指定次数的 ABC
// 线程操作资源类
// 判断干活唤醒
// 防止虚假唤醒
public class PrintABCAlternately {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(50));

    public static void main(String[] args) {
//        CompletableFuture.supplyAsync()
    }
}

// synchronized + wait/notify
class ShareResourceSynchronized {
    private int times; // 打印次数
    private int state; // 状态值 （用于判断的标志）
    private static final Object objectLock = new Object(); // 对象锁 （被争抢的资源）
    public ShareResourceSynchronized(int times) {
        this.times = times;
    }
    public void printABC(int targetState) {
        synchronized (objectLock) {
            for (int i = 0; i < times;) {
                // 判断
                while (state % 3 != targetState) { // while 防止虚假唤醒
                    try {
                        objectLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 干活
                System.out.print(Thread.currentThread().getName());
                if (state % 3 == 2)
                    System.out.print("|");
                i++;
                state++;
                objectLock.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ShareResourceSynchronized resourceSynchronized = new ShareResourceSynchronized(10);
        new Thread(() -> {
            resourceSynchronized.printABC(0);
        }, "A").start();
        new Thread(() -> {
            resourceSynchronized.printABC(1);
        }, "B").start();
        new Thread(() -> {
            resourceSynchronized.printABC(2);
        }, "C").start();
    }
}

// ReentrantLock & Condition + await/signal
class ShareResourceLockAndCondition {
    private static int state; // 状态值，保证三个线程之间交替打印 0-A 1-B 2-C
    private static int times; // 打印次数
    private static Lock lock = new ReentrantLock();

    private static Condition ca = lock.newCondition(); // A
    private static Condition cb = lock.newCondition(); // B
    private static Condition cc = lock.newCondition(); // C

    public static void printABC(int targetState, Condition currentThread, Condition nextThread) {
        for (int i = 0; i < times;) {
            lock.lock();
            try {
                // 判断
                // while 循环避免虚假唤醒
                while (state % 3 != targetState) {
                    currentThread.await();
                }
                // 干活
                System.out.print(Thread.currentThread().getName());
                if (state % 3 == 2) {
                    System.out.print("|");
                }
                // 唤醒
                i++;
                state++;
                nextThread.signal(); // 唤醒下一个线程
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public ShareResourceLockAndCondition(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        ShareResourceLockAndCondition resourceLockAndCondition = new ShareResourceLockAndCondition(10);
        new Thread(() -> {
            resourceLockAndCondition.printABC(0, ca, cb);
        }, "A").start();
        new Thread(() -> {
            resourceLockAndCondition.printABC(1, cb, cc);
        }, "B").start();
        new Thread(() -> {
            resourceLockAndCondition.printABC(2, cc, ca);
        }, "C").start();
    }

}


