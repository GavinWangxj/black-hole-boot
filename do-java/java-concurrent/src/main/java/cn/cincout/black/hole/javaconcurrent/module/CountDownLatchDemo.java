package cn.cincout.black.hole.javaconcurrent.module;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-17
 * @sine 1.8
 */

class TaskPortion implements Runnable {
    private static int counter;
    private final int id = counter++;
    private static Random random = new Random(47);

    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(this + " completed.");
    }

    @Override
    public String toString() {
        return "TaskPortion{" +
                "id=" + id +
                '}';
    }
}

public class CountDownLatchDemo {
    static int SIZE = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(latch));
        }
        try {
            latch.await();
            System.out.println("launched all tasks. cost " + (System.currentTimeMillis() - begin) );
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
