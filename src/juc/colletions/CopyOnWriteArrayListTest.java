package juc.colletions;

import java.util.*;
import java.util.concurrent.*;

/**
 * @see CopyOnWriteArrayList
 * @see ReentrantLock
 * 一个重入锁
 * 普通数组 transient volatile Object[] array;
 * copy实现动态扩张
 * @see Arrays#copyOf(Object[], int, Class)
 *
 * CopyOnWriteArraySet通过CopyOnWriteArrayList实现的,增加了是否存在判断
 *
 *   (01) 当list是CopyOnWriteArrayList对象时，程序能正常运行。
 *   (02) 当list是ArrayList对象时，程序会产生ConcurrentModificationException异常。
 *   (03). 因为通常需要复制整个基础数组，所以可变操作（add()、set() 和 remove() 等等）的开销很大。
 *   (04). 迭代器支持hasNext(), next()等不可变操作，但不支持可变 remove()等操作。
 *   (05). 使用迭代器进行遍历的速度很快，并且不会与其他线程发生冲突。在构造迭代器时，迭代器依赖于不变的数组快照。
 *   小型应用使用，总之效率很低
 * @author qq491
 */
public class CopyOnWriteArrayListTest {

    //private static List<String> list = new ArrayList<String>();
    private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {

        // 同时启动两个线程对list进行操作！
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value = null;
        Iterator iter = list.iterator();
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
                String val = Thread.currentThread().getName()+"-"+i;
                list.add(val);
                printAll();
            }
        }
    }
}
