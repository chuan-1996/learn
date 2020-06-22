package juc.colletions;

import java.util.*;
import java.util.concurrent.*;

/**
 * @see ReentrantLock
 * @see Condition
 *一个重入锁ReentrantLock
 * 和两个Condition  notempty notnull
 *
 * 02). ArrayBlockingQueue内部是通过Object[]数组保存数据的，也就是说ArrayBlockingQueue本质上是通过数组实现的。ArrayBlockingQueue的大小，即数组的容量是创建ArrayBlockingQueue时指定的。
 * (03). ArrayBlockingQueue与ReentrantLock是组合关系，ArrayBlockingQueue中包含一个ReentrantLock对象(lock)。ReentrantLock是可重入的互斥锁，
 * ArrayBlockingQueue就是根据该互斥锁实现“多线程对竞争资源的互斥访问”。而且，ReentrantLock分为公平锁和非公平锁，
 * 关于具体使用公平锁还是非公平锁，在创建ArrayBlockingQueue时可以指定；而且，ArrayBlockingQueue默认会使用非公平锁。
 * (04). ArrayBlockingQueue与Condition是组合关系，ArrayBlockingQueue中包含两个Condition对象(notEmpty和notFull)。
 * 而且，Condition又依赖于ArrayBlockingQueue而存在，通过Condition可以实现对ArrayBlockingQueue的更精确的访问 --
 * (01)若某线程(线程A)要取数据时，数组正好为空，则该线程会执行notEmpty.await()进行等待；当其它某个线程(线程B)向数组中插入了数据之后，会调用notEmpty.signal()唤醒“notEmpty上的等待线程”。
 * 此时，线程A会被唤醒从而得以继续运行。
 * (02)若某线程(线程H)要插入数据时，数组已满，则该线程会它执行notFull.await()进行等待；当其它某个线程(线程I)取出数据之后，会调用notFull.signal()唤醒“notFull上的等待线程”。
 * 此时，线程H就会被唤醒从而得以继续运行。
 *
 * // 移除此队列中所有可用的元素，并将它们添加到给定 collection 中。
 *@see ArrayBlockingQueue#drainTo(Collection)
 * // 最多从此队列中移除给定数量的可用元素，并将这些元素添加到给定 collection 中。
 *@see ArrayBlockingQueue#drainTo(Collection, int)
 * // 将指定的元素插入到此队列的尾部（如果立即可行且不会超过该队列的容量），在成功时返回 true，如果此队列已满，则返回 false。
 *@see ArrayBlockingQueue#offer(Object)
 * // 将指定的元素插入此队列的尾部，如果该队列已满，则在到达指定的等待时间之前等待可用的空间。
 *@see ArrayBlockingQueue#offer(Object, long, TimeUnit)
 * // 获取但不移除此队列的头；如果此队列为空，则返回 null。
 *@see ArrayBlockingQueue#peek()
 * // 获取并移除此队列的头，如果此队列为空，则返回 null。
 *@see ArrayBlockingQueue#poll()
 * // 获取并移除此队列的头部，在指定的等待时间前等待可用的元素（如果有必要）。
 *@see ArrayBlockingQueue#poll(long timeout,TimeUnit unit)
 * // 将指定的元素插入此队列的尾部，如果该队列已满，则等待可用的空间。
 *@see ArrayBlockingQueue#put
 * // 返回在无阻塞的理想情况下（不存在内存或资源约束）此队列能接受的其他元素数量。
 */
public class ArrayBlockingQueueTest{

    //private static Queue<String> queue = new LinkedList<String>();
    private static Queue<String> queue = new ArrayBlockingQueue<String>(20,false);
    public static void main(String[] args) {

        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value;
        Iterator iter = queue.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // “线程名” + "-" + "序号"
                String val = Thread.currentThread().getName()+i;
                queue.add(val);
                // 通过“Iterator”遍历queue。
                printAll();
            }
        }
    }
}
