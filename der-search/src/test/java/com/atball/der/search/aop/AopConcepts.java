package com.atball.der.search.aop;

import org.aspectj.lang.JoinPoint;

public class AopConcepts {

    // AspectJ 是一种Aop技术
    // Aop 是一种思想
    // Spring Aop 是对Aop的一种技术，和AspectJ一样是一种技术
    // Spring Aop 是运行时织入 AspectJ是编译时织入

    // Aop使用场景
    /**
     *
     */

    /**
     *
     *  Aspect: 切面   join point、advice、pointCut 的一个载体
     *
     *
     *  join point： 连接点 目标对象中的方法
     *
     *
     *
     *  advice：通知  内容 + 方法
     *
     *
     *
     *  pointCut： 切点 连接点的集合就是切点
     *
     *
     *
     *  target：  目标对象 原始对象（需要进行切入代码的对象）-> 织入后变成 Aop Proxy
     *
     *
     *
     *  weaving：织入   写的切入方法切到目标方法的连接点的过程称作织入
     *
     *
     *
     *  Aop Proxy： 代理对象 包含了原始对象的代码和增加后的代码的那个对象
     */

     // JoinPoint的方法
    /**
     * 1、 public Object[] getArgs(); 获取连接点方法运行时的入参列表
     * 2、Signature getSignature();   获取连接点的方法签名对象
     * 3、Object getTarget();         获取连接点所在的目标对象
     * 4、Object getThis();           获取代理对象本身
     *    proceed                     有重载，有个带参数的方法，可以修改目标方法的参数
     */


    // 切面的模型（作用域）
    /**
     * perthis
     */

    /**
     *
     *     Spring 5.x中AOP默认依旧使用JDK动态代理
     *     SpringBoot 2.x开始，AOP为了解决使用JDK动态代理可能导致的类型转换异常，而使用CGLIB。
     *     在SpringBoot 2.x中，AOP如果需要替换使用JDK动态代理可以通过配置项spring.aop.proxy-target-class=false来进行修改，proxyTargetClass配置已无效。
     *     原文链接：https://blog.csdn.net/myli92/article/details/127586235
     */


}
