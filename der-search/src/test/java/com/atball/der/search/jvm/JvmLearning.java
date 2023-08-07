package com.atball.der.search.jvm;

public class JvmLearning {

    // 基本命令
    /**
     * jps: 查看本地正在运行的java进程和进程ID（PID）
     *
     *
     * jinfo pid: 查看指定pid的所有JVM信息
     *          1）jinfo -flags pid：查询虚拟机运行参数信息。
     *          2）jinfo -flag name pid：查询具体参数信息，如jinfo -flag UseSerialGC 42324，查看是否启用UseSerialGC
     *
     *
     * jmap
     *          1）jmap -heap pid：查看堆内存设置和使用情况（JDK11 使用jhsdb jmap --heap --pid pid）
     *          2）jmap -histo pid：输出heap的直方图，包括类名，对象数量，对象占用大小
     *          3）jmap -histo:live pid   同上，只输出存活对象信息
     *          4）jmap -clstats pid：输出加载类信息
     *          5）jmap -help：jmap命令帮助信息
     *
     * jstat：Java虚拟机统计工具，全称“Java Virtual Machine statistics monitoring tool”。可以用于监视JVM各种堆和非堆内存大小和使用量
     *         1）jstat -class pid：输出加载类的数量及所占空间信息。
     *         2）jstat -gc pid：输出gc信息，包括gc次数和时间，内存使用状况（可带时间和显示条目参数）
     *    统计列含义：
     *          S0C：第一个幸存者区（Survivor Space）容量
     *          S1C：第二个幸存者区（Survivor Space）容量
     *          S0U：第二个幸存者区使用量
     *          S1U：第二个幸存者区使用量
     *          EC：伊甸园去容量
     *          EU：伊甸园区使用量
     *          OC：Old Generation区容量
     *          OU：Old Generation使用量
     *          MC：Mataspace区容量
     *          MU：Mataspace区实际使用量
     *          CCSC：压缩类空间大小（不是很懂，先标记一下）
     *          CCSU：压缩类空间使用率（不是很懂，先标记一下）
     *          YGC：年轻代垃圾回收次数
     *          YGCT：年轻代垃圾回收时间
     *          FGC：年老代垃圾回收次数
     *          FGCT：年老代垃圾回收时间
     *          GCT：总垃圾回收时间
     *
     *
     *          S0：S0C区域使用率
     *          S0：S1C区域使用率
     *          E：伊甸园去使用率
     *          O：Old Generation使用率，OU/OC
     *          M：Matespace区使用率，MU/MC
     *          CCS：压缩类空间使用率
     */


    // JVM性能调优
    /**
     *  JVM内存结构
     *  类加载机制和JDK的调优工具命令
     *  GC日志格式
     *  GC调优实战
     */

    // JVM的基本结构
    /**
     * JVM由三个主要的子系统构成
     *      类加载子系统  javac 命名编译文件  javap -c xxx.class > xxx.txt
     *      运行时数据区（内存结构）{堆、栈、本地方法栈、方法区、程序计数器}
     *             堆和方法区是线程共享的
     *             栈、本地方法栈、程序计数器是线程私有，随着线程创建而存在，线程销毁而消失
     *
     *      执行引擎：
     *      本地方法栈：存放native方法的地方
     *      方法区：常量池，class文件 堆外内存
     *
     *
     *
     */


    // 线程
    /**
     * 程序计数器，本地方法栈、栈（栈帧{操作数栈，局部变量表、动态链接、方法出口,}  ）
     * 程序计数器: 指针（指向下一个需要运行的字节码指令地址）
     * 栈: java执行方法时的内存模型 一个线程对应一个栈
     * 局部变量表：存放方法执行时的局部变量
     * 方法出口：指向调用者的地址 方法结束（分为正常退出或者异常退出）（栈帧返回值需要返回的地址）
     * 动态链接：
     *
     */
    // 类的生命周期
    /**
     * 加载（将.class文件从磁盘读到内存）、验证（验证字节码文件的正确性）、准备（给静态变量分配内存，并赋予默认值）
     * 、解析、初始化、使用、卸载
     */
    // 类加载器
    /**
     * 启动类加载器
     * bootstrapClassLoader
     *
     * 扩展类加载器
     * extensionClassLoader
     *
     * ApplicationClassLoader
     * 系统类加载器
     *
     * customClassLoader
     * 用户 自定义加载器
     *
     *
     * 类加载机制
     *      双亲委派机制：类加载先向上委托，在向下
     *         优点：沙箱安全机制: 比如自己写的String.class类不会被加载，这样可以防止核心库被随意篡改
     *              避免类的重复加载：当父ClassLoader已经被加载了该类的时候，就不要子加载器再加载一次
     *
     *      打破双亲委派机制
     */
}

