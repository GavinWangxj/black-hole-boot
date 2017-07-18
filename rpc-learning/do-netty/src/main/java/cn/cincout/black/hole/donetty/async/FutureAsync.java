package cn.cincout.black.hole.donetty.async;

import java.util.concurrent.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
public class FutureAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("i am task1.");
            }
        };

        Callable<Integer> task2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Integer(100);
            }
        };

        Future<?> f1 = executorService.submit(task1);
        Future<Integer> f2 = executorService.submit(task2);

        System.out.println("task1 is completed? " + f1.isDone());
        System.out.println("task2 is campleted? " + f2.isDone());

        while (f1.isDone()) {
            System.out.println("task1 completed");
            break;
        }

        while (f2.isDone()) {
            System.out.println("task2 completed. value " + f2.get());
            break;
        }
    }
}


