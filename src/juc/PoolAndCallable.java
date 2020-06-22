package juc;

import lock.LockSupportTest1;

import java.util.concurrent.*;

/**
 * @author qq491
 * @see ThreadPoolExecutor
 * @param corePoolSize the number of threads to keep in the pool, even
 *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
 * @param maximumPoolSize the maximum number of threads to allow in the
 *        pool
 * @param keepAliveTime when the number of threads is greater than
 *        the core, this is the maximum time that excess idle threads
 *        will wait for new tasks before terminating.
 * @param unit the time unit for the {@code keepAliveTime} argument
 * @param workQueue the queue to use for holding tasks before they are
 *        executed.  This queue will hold only the {@code Runnable}
 *        tasks submitted by the {@code execute} method.
 *
 *      ArrayBlockingQueue：基于数组、有界，按 FIFO（先进先出）原则对元素进行排序；
 *                  两个条件 一个重入锁 读写通用
 *      LinkedBlockingQueue：基于链表，按FIFO （先进先出） 排序元素，吞吐量通常要高于 ArrayBlockingQueue；
 *                  因为他有两个条件和两个重入锁 读写分用，锁更细粒度
 *      SynchronousQueue：每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于 LinkedBlockingQueue；
 *                  因为他是CAS的
 *      PriorityBlockingQueue：具有优先级的、无限阻塞队列。
 *
 * @param handler the handler to use when execution is blocked
 *        because the thread bounds and queue capacities are reached
 *
 *      AbortPolicy	抛出RejectedExecutionException
 *      DiscardPolicy	什么也不做，直接忽略
 *      DiscardOldestPolicy	丢弃执行队列中最老的任务，尝试为当前提交的任务腾出位置
 *      CallerRunsPolicy	直接由提交任务者执行这个任务
 *
 * 普通的
 * @see ExecutorService   这是顺序的(通过下标访问，无法得知完成顺序)
 * 结果的输出和线程的放入顺序 有关(如果前面的没完成，就算后面的哪个完成了也得等到你的牌号才能输出！)，so阻塞耗时
 *
 * @see CompletionService
 * CompletionService<Integer> completion = new ExecutorCompletionService<Integer>(executor);
 * 结果的输出和线程的放入顺序 无关(谁完成了谁就先输出！主线程总是能够拿到最先完成的任务的返回值，而不管它们加入线程池的顺序)，so很大大缩短等待时间
 *
 *总的来说他们的执行时间相同，但是 CompletionService  会检索谁做完了，提前输出结果
 *
 * 为什么future.get()可以阻塞当前线程直到线程池内的线程返回结果
 * {@linkplain java.util.concurrent.locks.LockSupport#park();}
 * @see LockSupportTest1
 */
public class PoolAndCallable {
    public static void main(String[] args) throws Exception{

        final int j = 5;
        ExecutorService executor = new ThreadPoolExecutor(
                2,
                2,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),
                new ThreadPoolExecutor.DiscardPolicy());

        CompletionService<Integer> completion = new ExecutorCompletionService<Integer>(executor);

        Future[] futures = new Future[j];
        for (int i = 0; i < j; i++) {
            futures[i] = executor.submit(new Task(i));
        }

        for (int i = 0; i < j; i++) {
            System.out.println(futures[i].get());
        }
        //future.get()是阻塞的方法哦
        System.out.println("ExecutorService完毕  可以看出获取的结果总是 0 1 3 6 10 并不是谁做完了谁就可以输出，" +
                "因为我们只能按照future[]的下标来访问future");


        for (int i = 0; i < j; i++) {
            completion.submit(new Task(i));
        }

        /*
         *  CompletionService 无阻塞获取结果的核心就是take()函数直接检索哪个任务执行完毕，返回这个任务的future
         */
        for (int i = 0; i < j; i++) {
            System.out.println(completion.take().get());
        }
        System.out.println("CompletionService完毕  可以看出谁做完了谁就可以输出，" +
                "因为我们使用take()来获得已经做完了的future!");

        /*
         *不推荐使用Future future = executor.submit(new Task(i))提交任务,如果要异步执行，稍后获得结果必须扩大future的域
         * 而普通的ExecutorService只能这样做
         *
         Future[] futures = new Future[j];
         for (int i = 0; i < j; i++) {
            futures[i] = executor.submit(new Task(i))

         }
         for (int i = 0; i < j; i++) {
            futures[i].get()
         }
         *
         * 否则只能在循环内获取结果
         for (int i = 0; i < j; i++) {
            Future future = executor.submit(new Task(i))
            future.get()
         }
         * future.get()获取结果的方法为阻塞方法，无法实现异步
         * 此时应使用ExecutorCompletionService提交和获得结果,即程序内的方法
         * 此外提交的任务必须为Callable才能获取返回值和处理异常，Runnable不行
         */
        executor.shutdown();
    }

    /*普通的runnable*/
    static class DivTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":hello world");
        }
    }

    static class Task implements java.util.concurrent.Callable<Integer> {
        int a;
        Task(int a){
            this.a=a;
        }
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for(int i=0;i<=a;++i){
                sum+=i;
            }
            System.out.println(Thread.currentThread().getName()+ " **0-"  + a + "累加计算完毕" + sum );
            return sum;
        }
    }
}
