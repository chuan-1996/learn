package lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

/**
 * @author qq491
 * 当这5个线程达到一定的条件时，执行某项任务
 */
public class CyclicBarrierTest2 {

    private static int SIZE = 5;
    private static CyclicBarrier cb;

    static class BarrierThread implements Runnable{
        @Override
        public void run() {
            System.out.println("CyclicBarrier's parties is: "+ cb.getParties());
        }
    }
    static class InnerThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
                // 将cb的参与者数量加1
                cb.await();
                // cb的参与者数量等于5时，才继续往后执行
                System.out.println(Thread.currentThread().getName() + " continued.");
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        cb = new CyclicBarrier(SIZE, new BarrierThread());
        // 新建5个任务
        for(int i=-SIZE; i<SIZE; i++){
            new InnerThread().start();
        }
    }
}
