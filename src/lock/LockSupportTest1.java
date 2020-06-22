package lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author qq491
 * @see LockSupport 阻塞一个进程不需要在同步代码块里使用了！
 * // 返回提供给最近一次尚未解除阻塞的 park 方法调用的 blocker 对象，如果该调用不受阻塞，则返回 null。
 * @see LockSupport#getBlocker(Thread t)
 * // 为了线程调度，禁用当前线程，除非许可可用。
 * @see LockSupport#park()
 * // 为了线程调度，在许可可用之前禁用当前线程。
 * @see LockSupport#park(Object blocker)
 * // 为了线程调度禁用当前线程，最多等待指定的等待时间，除非许可可用。
 * @see LockSupport#parkNanos(long nanos)
 * // 为了线程调度，在许可可用前禁用当前线程，并最多等待指定的等待时间。
 * @see LockSupport#parkNanos(Object blocker, long nanos)
 * // 为了线程调度，在指定的时限前禁用当前线程，除非许可可用。
 * @see LockSupport#parkUntil(long deadline)
 * // 为了线程调度，在指定的时限前禁用当前线程，除非许可可用。
 * @see LockSupport#parkUntil(Object blocker, long deadline)
 * // 如果给定线程的许可尚不可用，则使其可用。
 * @see LockSupport#unpark(Thread thread)
 */
public class LockSupportTest1 {

    private static Thread mainThread;

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");

        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + " start ta");
        ta.start();

        System.out.println(Thread.currentThread().getName() + " block");

        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName() + " continue");
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " wakup others");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }
}
