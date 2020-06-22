package ThreadTest;

import java.util.concurrent.*;

public class pool{
	public static void fix(){
		//begin FIXED
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
		System.out.println("固定数量线程池开始");
		for(int i=0;;i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ", " + index);
					Thread.sleep(2000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}

			});
		}
		//fixedThreadPool.shutdown();
		//end FIXED

	}
	public static void schedule(){
		//begin SCHEDULED
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		System.out.println("计划任务线程池开始");
		System.out.println("before:" + System.currentTimeMillis()/1000);
		scheduledThreadPool.schedule(new Runnable() {
		  @Override
		  public void run() {
		    System.out.println("延迟3秒执行的哦 :" + System.currentTimeMillis()/1000);
		  }
		}, 3, TimeUnit.SECONDS);
		System.out.println("after :" +System.currentTimeMillis()/1000);
		System.out.println("before:" + System.currentTimeMillis()/1000);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
		    System.out.println("延迟1秒之后，3秒执行一次:" +System.currentTimeMillis()/1000);
		  }
		}, 1, 3, TimeUnit.SECONDS);
		System.out.println("after :" +System.currentTimeMillis()/1000);
		//scheduledThreadPool.shutdown();
		//end SCHEDULED
	}

	public static void	single(){
		//begin SIGNLE
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		System.out.println("单线程线程池开始");
		for (int i=0;;i++) {
		  final int index = i;
		  singleThreadExecutor.execute(new Runnable() {
		    @Override
		    public void run() {
		      try {
		         System.out.println(Thread.currentThread().getName() + "," + index);
		        Thread.sleep(2000);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
		  });
		}
		//singleThreadExecutor.shutdown();
		//end SIGNLE
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//fix();
		//schedule();
		single();
	}
}


