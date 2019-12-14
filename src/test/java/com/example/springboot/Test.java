package com.example.springboot;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-06-24 10:15
 **/
public class Test {



    public static void main(String[] args) {


//        Stopwatch stopwatch = Stopwatch.createStarted();
//        String  param="";
//        switch (param){
//            case "null":
//                System.out.println("null");
//                break;
//            default:
//                System.out.println("Ok");
//        }
//        stopwatch.stop();
//        long elapsed = stopwatch.elapsed(TimeUnit.MICROSECONDS);
//        System.out.println("耗时："+elapsed);
//
//        stopwatch.start();
//        try {
//            Thread.sleep(5000);
//            stopwatch.stop();
//            elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
//            System.out.println("耗时2："+elapsed);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        AtomicInteger atomicInteger = new AtomicInteger(11);
        // valueOffset  atomicInteger value值的地址
//        new Thread(() -> {
//            int i = atomicInteger.incrementAndGet();
//            System.out.println("thread1 "+atomicInteger.get());
//        }).start();
//        for (int i = 0; i <100 ; i++) {
//            new Thread(() -> System.out.println(Thread.currentThread().getName()+": "+  atomicInteger.incrementAndGet())).start();
//        }


        ReentrantLock reentrantLock = new ReentrantLock();
        AtomicReference<Integer> count= new AtomicReference<>(0);

        // 可重入非公平锁 默认为非公平锁。
//        for (int i = 0; i <100 ; i++) {
//            new Thread(() ->{
//                reentrantLock.lock();
//
//                count.getAndSet(count.get() + 1);
//                System.out.println(Thread.currentThread().getName()+": "+count.get());
//                reentrantLock.unlock();
//
//            }).start();
//        }

        //可重入公平锁
        ReentrantLock reentrantLock2 = new ReentrantLock(true);
        for (int i = 0; i <10 ; i++) {
            new Thread(() ->{
                reentrantLock2.lock();

                count.getAndSet(count.get() + 1);
                System.out.println(Thread.currentThread().getName()+": "+count.get());
                reentrantLock2.unlock();

            }).start();
        }


    }

}
