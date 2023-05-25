package com.atball.der.search.jmm;

public class JMMLearning {


    private void test() {
    }


    static class DoubleCheckLock {
        private static DoubleCheckLock instance;

        private DoubleCheckLock() {
        }

        public static DoubleCheckLock getInstance() {
            //第一次检测
            if (instance == null) {
                //同步
                synchronized (DoubleCheckLock.class) {
                    if (instance == null) {
                        //多线程环境下可能会出现问题的地方
                        instance = new DoubleCheckLock();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 上述代码一个经典的单例的双重检测的代码，这段代码在单线程环境下并没有什么问
     * 题，但如果在多线程环境下就可以出现线程安全问题。原因在于某一个线程执行到第一次检
     * 测，读取到的instance不为null时，instance的引用对象可能没有完成初始化。
     * 因为instance = new DoubleCheckLock();可以分为以下3步完成(伪代码)
     *
     * 1 memory = allocate();//1.分配对象内存空间
     * 2 instance(memory);//2.初始化对象
     * 3 instance = memory;//3.设置instance指向刚分配的内存地址，此时instance
     */

    // 注意：
    /**
     * 我们经常都会这么说：
     *
     * ①、volatile能保证内存可见性、禁止指令重排序但是不能保证原子性。
     *
     * ②、synchronized能保证原子性、可见性和有序性。
     *
     *  注意：这里的有序性并不是代表能禁止指令重排序。
     */
}



