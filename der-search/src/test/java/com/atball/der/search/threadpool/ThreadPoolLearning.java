package com.atball.der.search.threadpool;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolLearning {

    // 一个线程池中的线程异常了，那么线程池会怎么处理这个线程?

    /**
     *  1.抛出堆栈异常 ---这句话对了一半！
     *  2.不影响其他线程任务 ---这句话全对！
     *  3.这个线程会被放回线程池 ---这句话全错！
     *      当一个线程池里面的线程异常后:
     *      当执行方式是execute时,可以看到堆栈异常的输出。
     *      当执行方式是submit时,堆栈异常没有输出。但是调用Future.get()方法时，可以捕获到异常。
     *      不会影响线程池里面其他线程的正常执行。
     *      线程池会把这个线程移除掉，并创建一个新的线程放到线程池中。
     */


    // 自定义拒绝策
//    public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

//        //这个地方不能注入
//        private RedisTemplate redisTemplate;
//        final private String THREAD_TASK_KEY = "threadPoolTask";

//        /**
//         * 当线程池最大线程数量达到最大时，且有新任务提交就会执行拒绝策略
//         *
//         * @param r        被拒绝的任务
//         * @param executor 当前线程池
//         */
//        @Override
//        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//            //控制台打印代替日志
//            System.out.println("执行自定义拒绝策略，提交任务");
//            //redis存储对象必须要实现序列化接口，但是Runnable是jdk接口，所以这里是使用阿里的fastjosn来实现序列化
//            String s = JSON.toJSONString(r);
//            //从左进一个任务
//            redisTemplate.opsForList().leftPush(THREAD_TASK_KEY, s);
//        }

//        public MyRejectedExecutionHandler(RedisTemplate redisTemplate) {
//            this.redisTemplate = redisTemplate;
//        }

//        原文链接：https://blog.csdn.net/yeluowan/article/details/126009681
//    }

}
