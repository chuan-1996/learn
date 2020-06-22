package ThreadTest;

class MyThread3 extends Thread{
    public MyThread3(String name) {
        super(name);
    }

    public void run(){
        System.out.println(Thread.currentThread().getName()+" is running");
    }
};

/**
 * (01) Thread.currentThread().getName()是用于获取“当前线程”的名字。当前线程是指正在cpu中调度执行的线程。
 * (02) mythread.run()是在“主线程main”中调用的，该run()方法直接运行在“主线程main”上。
 * (03) mythread.start()会启动“线程mythread”，“线程mythread”启动之后，会调用run()方法；此时的run()方法是运行在“线程mythread”上。
 * @author qq491
 */
public class RunDemo {
    public static void main(String[] args) {
        Thread mythread=new MyThread3("mythread");

        System.out.println(Thread.currentThread().getName()+" call mythread.run()");
        mythread.run();

        System.out.println(Thread.currentThread().getName()+" call mythread.start()");
        mythread.start();
    }
}
