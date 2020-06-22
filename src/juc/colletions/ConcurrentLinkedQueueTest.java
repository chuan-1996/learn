package juc.colletions;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 *node链表
 * @see ConcurrentLinkedQueue
 *  class Node<E> {
 *    volatile E item;
 *    volatile Node<E> next;}
 *
 * Node<E> head
 * Node<E> tail
 * 无界
 * ConcurrentLinkedQueue是线程安全的队列，它适用于“高并发”的场景。
 * 它是一个基于链接节点的无界线程安全队列，按照 FIFO（先进先出）原则对元素进行排序。队列元素中不可以放置null元素（内部实现的特殊节点除外）。
 *
 * //将指定元素插入此队列的尾部。
 * @see ConcurrentLinkedQueue#offer(Object)
 * // 获取但不移除此队列的头；如果此队列为空，则返回 null。
 * @see ConcurrentLinkedQueue#peek()
 * // 获取并移除此队列的头，如果此队列为空，则返回 null。
 * @see ConcurrentLinkedQueue#poll()
 * // 从队列中移除指定元素的单个实例（如果存在）。
 * @see ConcurrentLinkedQueue#remove(Object o)
 * @author qq491
 */
public class ConcurrentLinkedQueueTest {

    //private static Queue<String> queue = new LinkedList<String>();
    private static Queue<String> queue = new ConcurrentLinkedQueue<String>();
    public static void main(String[] args) {

        // 同时启动两个线程对queue进行操作！
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
                String val = Thread.currentThread().getName()+i;
                queue.add(val);
                printAll();
            }
        }
    }
}
