package lock.juclock;

import java.util.concurrent.locks.Lock;


/**
 * 仓库
 */
class Depot2 {

    private int size;
    private Lock lock;

    public Depot2() {
        this.size = 0;
    }

    public void produce(int val) {
        size += val;
        System.out.printf("%s produce(%d) --> size=%d\n",
                Thread.currentThread().getName(), val, size);
    }

    public void consume(int val) {
        size -= val;
        System.out.printf("%s consume(%d) <-- size=%d\n",
                Thread.currentThread().getName(), val, size);
    }
};

/**
 * 生产者
 */
class Producer2 {
    private Depot2 depot;
    public Producer2(Depot2 depot) {
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
class Customer2 {
    private Depot2 depot;

    public Customer2(Depot2 depot) {
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
 * 不是每次运行都是按照顺序执行的
 */
public class LockTest2 {
    public static void main(String[] args) {
        Depot2 mDepot = new Depot2();
        Producer2 mPro = new Producer2(mDepot);
        Customer2 mCus = new Customer2(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
