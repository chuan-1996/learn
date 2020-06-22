package ThreadTest;

class MyThread5 extends Thread{
    public MyThread5(String name) {
        super(name);
    }

    @Override
    public void run(){
        for (int i=0; i<5; i++) {
            System.out.println(Thread.currentThread().getName()
                    +"("+Thread.currentThread().getPriority()+ ")"
                    +", loop "+i);
        }
    }
};

public class Priority {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()
                +"("+Thread.currentThread().getPriority()+ ")");

        Thread t1=new MyThread5("t1");
        Thread t2=new MyThread5("t2");
        t1.setPriority(1);
        t2.setPriority(10);
        t1.start();
        t2.start();
    }
}
