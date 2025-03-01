package lock.juclock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 仓库
 */
class Depot {

    private int size;
    private Lock lock;

    public Depot() {
        this.size = 0;
        this.lock = new ReentrantLock();
    }

    public void produce(int val) {
        lock.lock();
        try {
            size += val;
            System.out.printf("%s produce(%d) --> size=%d\n",
                    Thread.currentThread().getName(), val, size);
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            size -= val;
            System.out.printf("%s consume(%d) <-- size=%d\n",
                    Thread.currentThread().getName(), val, size);
        } finally {
            lock.unlock();
        }
    }
};

/**
 * 生产者
 */
class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    public void produce(final int val) {
        new Thread(()->{
            depot.produce(val);
        }).start();
    }
}

/**
 * 消费者
 */
class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        this.depot = depot;
    }

    public void consume(final int val) {
        new Thread(()->{
            depot.consume(val);
        }).start();
    }
}

/**
 * @author qq491
 * 每次运行都是按照顺序执行的
 * 参照{@link LockTest2} 可以看到lock的作用   等同于sychronized
 *
 * 这个模型存在两个问题：
 * (01) 现实中，仓库的容量不可能为负数。但是，此模型中的仓库容量可以为负数，这与现实相矛盾！
 * (02) 现实中，仓库的容量是有限制的。但是，此模型中的容量确实没有限制的！
 * 参照{@link LockTest3}，解决了以上问题
 *
 */
public class LockTest {
    public static void main(String[] args) {
        Depot mDepot = new Depot();
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
