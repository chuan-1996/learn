package ThreadTest;

class MyThread extends Thread{
    private int ticket=10;

    @Override
    public void run(){
        for(int i=0;i<20&&!isInterrupted();i++){
            if(this.ticket>0){
                System.out.println(this.getName()+" 卖票：ticket"+this.ticket--);
            }
        }
    }
};

class MyThread2 extends Thread{
    private int ticket=10;

    @Override
    public void run(){
        for(int i=0;i<20;i++){
            if(this.ticket>0){
                System.out.println(Thread.currentThread().getName()+" 卖票：ticket"+this.ticket--);
            }
        }
    }
};

/**
 * (01) wait()是让线程由“运行状态”进入到“等待(阻塞)状态”，而不yield()是让线程由“运行状态”进入到“就绪状态”。
 * (02) wait()是会线程释放它所持有对象的同步锁，而yield()方法不会释放锁。
 * join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
 *
 * 正确的循环方式 在循环条件中加入!isInterrupted()   不加也行，不过会抛出异常
 *while (!isInterrupted()) {
 *             // 执行任务...
 *         }
 *
 * @author qq491
 */
public class ThreadTest {
    public static void main(String[] args) throws Exception{
        // 启动3个线程t1,t2,t3；每个线程各卖10张票！
        MyThread2 t1=new MyThread2();
        MyThread t2=new MyThread();
        MyThread t3=new MyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.interrupt();

        t1.join();
        t2.join();
        t3.join();
        System.out.println("=================");
        MyThread2 mt2=new MyThread2();
        // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
        Thread t21=new Thread(mt2);
        Thread t22=new Thread(mt2);
        Thread t23=new Thread(mt2);
        t21.start();
        t22.start();
        t23.start();

    }
}
