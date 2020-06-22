package lock;

import java.util.concurrent.*;

/**
 * @author qq491
 * 获得POOL的使用方法，请参考
 * @see juc.PoolAndCallable
 */
public class SemaphoreTest1 {
    private static final int SEM_MAX = 10;
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(SEM_MAX);

        ExecutorService threadPool = new ThreadPoolExecutor(
                3,
                3,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),
                new ThreadPoolExecutor.DiscardPolicy());

        threadPool.execute(new MyThread(sem, 5));
        threadPool.execute(new MyThread(sem, 4));
        threadPool.execute(new MyThread(sem, 7));

        threadPool.shutdown();
    }
}

class MyThread extends Thread {
    /** 信号量 */
    private volatile Semaphore sem;
    /** 申请信号量的大小 */
    private int count;

    MyThread(Semaphore sem, int count) {
        this.sem = sem;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            // 从信号量中获取count个许可
            sem.acquire(count);
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " acquire count="+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放给定数目的许可，将其返回到信号量。
            sem.release(count);
            System.out.println(Thread.currentThread().getName() + " release " + count + "");
        }
    }
}
