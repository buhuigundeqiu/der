package com.atball.der.search.multithreading;

import com.atball.der.search.DerSearchApplicationTests;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MultithreadingLearning extends DerSearchApplicationTests {

    @Test
    public void MultithreadingTest() throws ExecutionException, InterruptedException {
        /**
         * 1、继承 Thread
         * ThreadImpl threadImpl = new ThreadImpl();
         * threadImpl.start(); // 启动线程
         * 2、实现 Runnable 接口
         * RunnableImpl runnableImpl = new RunnableImpl();
         * new Thread(runnableImpl).start();
         * 3、实现 Callable 接口 + FutureTask（可以拿到返回结果,可以处理异常）
         * new
         * 4、线程池
         */

        FutureTask<Integer> futureTask = new FutureTask<>(new MultithreadingLearning.CallableImpl());
        new Thread(futureTask).start();
        // 阻塞等待整个线程执行完成，获取返回结果
        Integer resutl = futureTask.get();


    }


    public static class CallableImpl implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return null;
        }
    }
}
