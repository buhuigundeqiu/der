package com.atball.der.search.jta;


public class DistributedTransactionLearning {

    /**
     *
     * 分布式事务前置理论基础
     *      ACID和分布式事务的区别
     *      CAP  BASE理论
     *      DTP 模型
     *
     *
     *
     * 分布式事务典型应用场景
     *
     *      跨库事务
     *
     *      分库分表 （shardingSphere分库分表的事务问题）
     *
     *      服务化
     *
     *
     *
     *
     *
     *   常见的分布式事务解决方案
     *
     *
     *                           2PC             TCC           可靠消息            最大努力通知
     *
     *
     *           一致性          强一致性       最终一致性        最终一致性             最终一致性
     *
     *
     *          吞吐量             低              中              高                  高
     *
     *
     *          实现复杂度          易              难              中                  易
     *
     *
     * TCC是比较常用的一种柔性事务方案
     *      开源的TCC框架:
     *          Tcc-Transaction
     *          Hmily
     *          ByteTCC
     *          EasyTransaction
     *          Seata
     *          TCC
     *
     *
     * 两阶段提交
     *
     *                       TCC                                 XA
     *
     *
     *    阶段一              try                              prepare
     *                 请求多个业务预留业务资源               询问每个RM是否可以提交事务
     *
     *
     *
     *                       confirm                          commit
     *                确认执行每个业务方的资源操作             提交每个RM上的事务分支
     *    阶段二
     *                       cancel                           rollback
     *                取消执行每个业务的资源操作               回滚每个RM上的事务分支
     *
     *
     *
     *   TCC 异常控制
     *        在微服务架构下，很有可能出现网络超时、重发、机器宕机等一系列的异常，出现空回滚、幂等、悬挂的问题。
     *
     *
     *
     * 思考：
     * 下面三种方案哪种更合理？
     *
     * 方案1：
     *    账户A
     *
     *       try：
     *          检查余额是否够30元                  //
     *          扣减30元
     *       confirm：
     *          空
     *       cancel：
     *          增加30元
     *
     *    账户B
     *
     *        try：
     *           增加30元                       // 账户B的先于账户A执行 导致账户B可能被金额透支
     *        confirm：
     *           空
     *        cancel：
     *           减少30元
     *
     *
     *  方案2：
     *     账户A
     *
     *        try：
     *           检查余额是否够30元               // 金额不够30 cancel会增加30元
     *           扣减30元
     *        confirm：
     *           空
     *        cancel：
     *           增加30元
     *
     *     账号B
     *        try：
     *          空
     *       confirm：
     *          增加30元
     *       cancel：
     *          空
     *
     *
     *  方案3：
     *     账号A
     *
     *        try：
     *           try幂等校验
     *           try悬挂处理
     *           检查余额是否够30元
     *           扣减30元
     *        confirm：
     *           空
     *        cancel：
     *           cancel幂等校验
     *           cancel空回滚处理
     *           增加可用余额30元
     *
     *     账户B
     *
     *         try：
     *           空
     *         confirm：
     *            confirm幂等校验
     *            正式增加30元
     *         cancel：
     *             空
     *
     *
     * TCC设计 -允许空回滚
     *      空回滚：在没有调用TCC资源Try方法的情况下，调用了二阶段的Cancel方法，Cancel方法需要识别出这是一个空回滚，
     *              然后直接返回成功。
     *      空回滚出现的原因:
     *             Try超时（丢包），分布式事务回滚触发Cancel，
     * 出现未收到Try，
     * 收到Cancel的情况
     *
     *
     *  使用 Hmily 框架完成TCC事务 事务的协调者整合在框架中（easy）
     *
     *      first step 引入包
     *
     *          <dependency>
     * 			    <groupId>org.dromara</groupId>
     * 			    <artifactId>hmily-springcloud</artifactId>
     * 			    <version>${hmily-springcloud.version}</version>
     * 		    </dependency>
     *
     * 	    second step
     * 	        在配置文件中配置后，会自动在指定的的数据中生成表
     *
     *
     * 	    third  step
     *          编写 try comfirm cancel 代码
     *          只要标记@Hmily就是try方法，在注解中指定confirm、cancel两个方法的名字
     *          @Trnasactional 注解还是正常的添加
     *
     *
     *
     *
     *
     *
     *
     */
}
