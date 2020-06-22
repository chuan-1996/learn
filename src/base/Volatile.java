package base;

/**
  控制子线程的工作内存
 * @author qq491
 */
public class Volatile {

    public static Boolean stop = false;

	static class testThread extends Thread{

	    @Override
	    public  void run(){
	    	int i = 1;
            while(!stop){
            	//此句会使JVM重新缓存子线程工作内存的stop副本
                //System.out.println("in thread: "  + " i: " + i);
                i++;
            }
            System.out.println("Thread stop i="+ i);
	    }
	}

	public volatile static Boolean stop2 = false; //该值不会出现副本，但并不能保证线程安全

	static class testThread2 extends Thread{

	    @Override
	    public  void run(){
	    	int i = 1;
            while(!stop2){
            	//此句会使JVM重新缓存子线程工作内存的stop副本
                //System.out.println("in thread: "  + " i: " + i);
                i++;
            }
            System.out.println("Thread2 stop i="+ i);
	    }
	}

    public static void main(String args[]) throws Exception {
    	testThread a = new testThread();
    	a.start();
    	testThread2 b = new testThread2();
    	b.start();
        Thread.sleep(1000);
        stop = true;
        stop2 = true;
        System.out.println("now, in main thread stop is: " + stop);
        a.join();
        b.join();
        System.out.println("main thread stop!");
    }
}
