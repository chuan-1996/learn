package lock.juclock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * 仓库
 *@param capacity;    // 仓库的容量
 *@param size;        // 仓库的实际数量
 *@param lock;        // 独占锁
 *@param fullCondition;            // 生产条件
 *@param emptyCondition;        // 消费条件
 */
class Depot3 {
    private int capacity;
    private int size;
    private Lock lock;
    private Condition fullCondition;
    private Condition emptyCondition;

    public Depot3(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            // left 表示“想要生产的数量”(有可能生产量太多，需多此生产)
            int left = val;
            while (left > 0) {
                // 库存已满时，等待“消费者”消费产品。
                while (size >= capacity)
                    fullCondition.await();

                // 获取“实际生产的数量”(即库存中新增的数量)
                // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                // 否则“实际增量”=“想要生产的数量”
                int inc = (size+left)>capacity ? (capacity-size) : left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, inc, size);
                // 通知“消费者”可以消费了。
                emptyCondition.signal();
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            int left = val;
            while (left > 0) {
                // 库存为0时，等待“生产者”生产产品。
                while (size <= 0)
                    emptyCondition.await();
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = Math.min(size, left);
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "capacity:"+capacity+", actual size:"+size;
    }
};

/**
 * 生产者
 */
class Producer3 {
    private Depot3 depot;

    public Producer3(Depot3 depot) {
        this.depot = depot;
    }

    public void produce(final int val) {
        new Thread(()-> {
            depot.produce(val);
        })
             .start();
    }
}

/**
 * 消费者
 */
class Customer3 {
    private Depot3 depot;

    public Customer3(Depot3 depot) {
        this.depot = depot;
    }

    public void consume(final int val) {
        new Thread(()->{
            depot.consume(val);
        }) .start();
    }
}

/**
 * @author qq491
 * Condition的使用
 */
public class LockTest3 {
    public static void main(String[] args) {
        Depot3 mDepot = new Depot3(100);
        Producer3 mPro = new Producer3(mDepot);
        Customer3 mCus = new Customer3(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
