package cn.cincout.black.hole.javaconcurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-7
 * @sine 1.8
 */
public class DoThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10));
        try {
            for (int i = 0; i < 20; i ++) {
                executor.execute(new DoThreadPoolExecutor.Job("jobNumb" + i));
                //printPoolInfo(executor);
            }
        } catch (RejectedExecutionException e) {
            System.out.println("in exception....");
            printPoolInfo(executor);
            e.printStackTrace();
        }

    }

    static class Job implements Runnable {
        private String jobNum;

        public Job(String jobNum) {
            this.jobNum = jobNum;
        }

        @Override
        public void run() {
            System.out.println("job " + jobNum + " is running...");
            while (true) {

            }
        }
    }

    static void printPoolInfo(ThreadPoolExecutor executor) {
        System.out.println("getActiveCount : \t" + executor.getActiveCount());
        System.out.println("getCompletedTaskCount : \t" + executor.getCompletedTaskCount());
        System.out.println("getCorePoolSize : \t" + executor.getCorePoolSize());
        System.out.println("getLargestPoolSize : \t" + executor.getLargestPoolSize());
        System.out.println("getMaximumPoolSize : \t" + executor.getMaximumPoolSize());
        System.out.println("getQueue.size : \t" + executor.getQueue().size());
        System.out.println("getPoolSize : \t" + executor.getPoolSize());
        System.out.println("getTaskCount : \t" + executor.getTaskCount());
        System.out.println("---------------------------------------------------");
    }
}
