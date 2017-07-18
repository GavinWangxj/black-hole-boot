package cn.cincout.black.hole.javaconcurrent.singleton;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-14
 * @sine 1.8
 */
public class Run {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setName("mythread");
        thread.start();
        try {
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("whether stop 1 " + Thread.interrupted());
            System.out.println("whether stop 2 " + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    public MyThread() {
        super();
        // current this
        System.out.println(this.getName());
        System.out.println(this.getClass());
        // current thread
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void run() {
        super.run();
        System.out.println(this.getName());
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 500000; i++) {
            //System.out.println("i=" + (i+1));
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("this.isInterrupted()");
                    throw new InterruptedException("I was interrupted.");
                }
                System.out.println("i = " + i);
            }
        } catch (InterruptedException e) {
            System.out.println("I was in catch.");
            e.printStackTrace();
            System.out.println("fuck...");
        }
        //System.out.println("thread end...");
    }

    public static void main(String[] args) {
        MyThread2 thread2 = new MyThread2();
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            thread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end...");
        System.out.println(Thread.currentThread().isInterrupted());
    }
}

