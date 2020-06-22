package ThreadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class SingleThread extends Thread{
    private  static final Ton ton = Ton.getInstance();
    @Override
    public void run(){
        System.out.println(this.getName()+" My turn：");
        ton.run();
    }
};

class SingleThreadLocal extends Thread{
    private  static final Ton ton = Ton.getInstance();
    @Override
    public void run() {
        System.out.println(this.getName()+" My turn：");
        ton.run2();
    }

};
class Ton{
    /**
     * 如果你想在单例模式下使用非静态的成员，如a，使用ThreadLocal封装！
     * 如果该成员类型为基本类型，则使用JUC包对应的ATOMIC类型
     * 此外无论使用何种类型只能保证单点并发，分布式改为使用共用缓存
     */
    private int a = 0;

    private static ThreadLocal<Integer> holder = new ThreadLocal<Integer>(){
        @Override
        public Integer initialValue(){
            return 0;
        }
    };

    private Ton(){
        holder.remove();
    }
    private static Ton instance = new Ton();
    public static Ton getInstance() {
        return instance;
    }

    public void run()  {
        for(int i =0;i<10;i++){
            a++;
            System.out.printf("%10s: %d \n",Thread.currentThread().getName(),a);
        }
    }

    public void run2()  {
        for(int i =0;i<10;i++){
            holder.set(Ton.holder.get()+1);
            System.out.printf("%10s: %d \n",Thread.currentThread().getName(),Ton.holder.get());
        }
    }
};

/**
 * ThreadLocal为每个线程保存了一个int副本
 */
public class SingletonTest {
    public static void main(String[] args) throws Exception {
        System.out.println("=================普通的单例在并发条件下的执行");
        SingleThread singleThread = new SingleThread();
        Thread t1 = new Thread(singleThread);
        Thread t2 = new Thread(singleThread);
        Thread t3 = new Thread(singleThread);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        ExecutorService executor = new ThreadPoolExecutor(
                3,
                3,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardPolicy());
        System.out.println("=================使用ThreadLocal在并发条件下的执行");
        SingleThreadLocal mt2=new SingleThreadLocal();
        executor.submit(mt2);
        executor.submit(mt2);
        executor.submit(mt2);
        executor.shutdown();
    }
}
