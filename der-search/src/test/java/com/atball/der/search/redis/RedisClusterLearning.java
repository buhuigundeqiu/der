package com.atball.der.search.redis;

public class RedisClusterLearning {

    // Redis 分布式集群 master选举策略，数据流向


    // redis 的持久化策略

    /**
     *  RDB 和 AOP
     *
     *
     *  redis的持久化方式有俩种，持久化策略有4种：
     *
     *   RDB（数据快照模式），定期存储，保存的是数据本身，存储文件是紧凑的
     *   AOF（追加模式），每次修改数据时，同步到硬盘(写操作日志)，保存的是数据的变更记录
     *   如果只希望数据保存在内存中的话，俩种策略都可以关闭
     *   也可以同时开启俩种策略，当Redis重启时，AOF文件会用于重建原始数据
     */
}
