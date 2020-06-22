package lock;

import lock.LockSupportTest1;

/**
 * @author qq491
 * wait和notify/notifyAll方法只能在同步代码块里用
 * wait和notify/notifyAll方法只能在同步代码块里用
 * wait和notify/notifyAll方法只能在同步代码块里用
 * wait和notify/notifyAll方法只能在同步代码块里用
 * wait和notify/notifyAll方法只能在同步代码块里用
 * wait和notify/notifyAll方法只能在同步代码块里用
 * @see LockSupportTest1 解决了这个问题(线程)
 * @see LockTest1 解决了这个问题(对象)
 */
public class WaitTest1 {

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");

        synchronized(ta) {
            try {
                System.out.println(Thread.currentThread().getName()+" start ta");
                ta.start();
                System.out.println(Thread.currentThread().getName()+" block");

                ta.wait();

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) { // 通过synchronized(this)获取“当前对象的同步锁”
                System.out.println(Thread.currentThread().getName()+" wakup others");
                notify();    // 唤醒“当前对象上的等待线程”
            }
        }
    }
}
