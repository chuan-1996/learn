package juc.colletions;

import java.util.*;
import java.util.concurrent.*;

 /**
 *   Segment数组 HashEntry数组  无限扩张 loadfator
 *   Segment<K,V> extends ReentrantLock
  *  Node<K,V> implements Map.Entry<K,V>
  *  Node<K,V>[] table
 *   ConcurrentHashMap是线程安全的哈希表，它是通过“锁分段”来保证线程安全的。
 *   ConcurrentHashMap将哈希表分成许多片段(Segment)，每一个片段除了保存哈希表之外，本质上也是一个“可重入的互斥锁”(ReentrantLock)。多线程对同一个片段的访问，是互斥的；
 *   但是，对于不同片段的访问，却是可以同步进行的。
 */
public class ConcurrentHashMapTest{

    //private static Map<String, String> map = new HashMap<String, String>();
    private static Map<String, String> map = new ConcurrentHashMap<String, String>();
    public static void main(String[] args) {

        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String key, value;
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            key = (String)entry.getKey();
            value = (String)entry.getValue();
            System.out.print(key+" - "+value+", ");
        }
        System.out.println();
    }

    /**
     * 使用此法遍历效率高，但无法执行remove
     */
    private static void printAll2() {
        for(Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey()+" - "+entry.getValue()+", ");
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
                map.put(String.valueOf(i), val);
                printAll2();
            }
        }
    }
}
