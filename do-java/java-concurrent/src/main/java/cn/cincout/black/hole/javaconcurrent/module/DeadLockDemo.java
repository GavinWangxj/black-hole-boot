package cn.cincout.black.hole.javaconcurrent.module;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-19
 * @sine 1.8
 */
public class DeadLockDemo implements Runnable {

    public String name;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if ("a".equals(name)) {
            synchronized (lock1) {
                try {
                    System.out.println("name is " + name);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("lock1 -> lock2");
                }
            }
        }

        if ("b".equals(name)) {
            synchronized (lock2) {
                try {
                    System.out.println("name is " + name);
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("lock2 -> lock1");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockDemo demo1 = new DeadLockDemo();
        demo1.setName("a");
        new Thread(demo1).start();
        Thread.sleep(5000);
        demo1.setName("b");
        new Thread(demo1).start();

    }
}
