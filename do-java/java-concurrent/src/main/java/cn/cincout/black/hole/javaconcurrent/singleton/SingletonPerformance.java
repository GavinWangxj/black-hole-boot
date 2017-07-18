package cn.cincout.black.hole.javaconcurrent.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-10
 * @sine 1.8
 */
public class SingletonPerformance {

    public static void main(String[] args) {
        long totalTime = 0;
        for (int i = 0; i < 5000; i++) {
            totalTime += runSingletonOneTime();
        }
        System.out.println("singleton time cost : " + totalTime / 100);

        totalTime = 0;

        for (int i = 0; i < 500; i++) {
            totalTime += runNewOneTime();
        }
        System.out.println("new time cost : " + totalTime / 100);
    }

    public static long runSingletonOneTime() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getJdbcTemplate();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            jdbcTemplate.save(5, Integer.toString(i));
        }
        return System.currentTimeMillis() - begin;
    }

    public static long runNewOneTime() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.save(1, Integer.toString(i));
        }
        return System.currentTimeMillis() - begin;
    }
}



class JdbcTemplate {

    private final static JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void save(long taskTime, String taskName) {
        Thread thread = new Thread(new Task(taskTime, taskName));
        thread.start();
    }
}

class Task implements Runnable {

    private final static Logger LOG = LoggerFactory.getLogger(Task.class);

    private final long taskTime;
    private final String taskNum;

    public Task(long taskTime, String taskNum) {
        this.taskTime = taskTime;
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        //LOG.info("task {} beginning ...", taskNum);
        try {
            Thread.sleep(taskTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //LOG.info("task {} ending ...", taskNum);
    }
}
