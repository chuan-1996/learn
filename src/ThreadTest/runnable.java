package ThreadTest;

/**
 * 此程序下创建500个Seller实例时，程序如果不使用静态成员lock加锁，而使用以下同步方法无效
 *
 * public synchronized void sell() 此法锁定实例内方法sell，但每个实例有自己的sell方法，故线程之间无竞争关系（锁无意义）
 *改进方法  化为静态方法，使500个实例使用同一方法，故有竞争关系
  public synchronized static void sell(){
 *
 * synchronized(this) 此法锁定Seller实例，因每个线程有自己的Seller实例，故无竞争关系
 * 改进方法  500个线程使用同一个实例对象，此时显然有竞争关系
  ThreadTest.Seller a = new ThreadTest.Seller();
  for(int i = 1 ; i <= 500;++i)
   	new Thread(a).start();

 */
class Seller extends Thread{
	public static Object lock = new Object();
	public static int ticket = 500;

     public synchronized static void sell(){
    	synchronized(lock){
            ticket--;
            System.out.println(Thread.currentThread()+":"+ ticket);

    	}
    }
    @Override
    public  void run(){
    	sell();
    }
}

public class runnable {

    public static void main(String[] args) {

    for(int i = 1 ; i <= 500;++i)
    	new Seller().start();

	}

}
