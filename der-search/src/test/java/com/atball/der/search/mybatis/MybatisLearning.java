package com.atball.der.search.mybatis;

public class MybatisLearning {

    // 为什么Mapper.xml文件中Id要和Mapper类中的方法名一致
    /**
     *                      命名空间                            方法名
     * String statementId = mapperInterface.getName() + "." + methodName;
     */
    // 自动装配需要set方法，另外属性是Class类型的会过滤，不进行自动装配


    // 根据OrderTabDao类型加载
    // @Autowired
    // private Map<String,OrderTabDao> map;



    // Mybatis一级缓存失效的问题
    /**
     * 原理分析（重点）
     *   结论：Spring将MyBatis的DefaultSqlSession类替换成了SqlSessionTemplate
     *
     *      MyBatis的一级缓存是基于SqlSession来实现的，对应MyBatis中sqlSession接口的默实认现类是DefaultSqlSession，如果执行的SQL相同时，并且使用的是同一个SqlSession对象，那么就会触发对应的缓存机制。
     *
     *      但是在Spring整合MyBatis后，Spring使用MyBatis不再是直接调用MyBatis中的信息，而是通过调用调用mybatis-spring.jar中的类，继而达到间接调用MyBatis的效果。但在mybatis-spring.jar中，引入了一个SqlSessionTemplate类，它和Spring的事务管理器共同配合，创建对应的SqlSession连接。
     *
     *      即在没有添加@Transactional注解的情况下，每调用一次查询SQL，就会通过SqlSessionTemplate去创建sqlSession，即相当于新创建一次连接，故而每次查询在调试结果看来就是一级缓存失效
     *
     *  为什么加了@Transactional注解就可以使用缓存？
     *
     *      核心就是注册的方法，我测试的场景是没有加@Transactional注解的时候，此处判断为false就不会再向缓存中添加数据。
     *
     *      当然如果判断成功就是会调用TransactionSynchronizationManager.registerSynchronization(new SqlSessionSynchronization(holder, sessionFactory))方法，将该sqlSession对象添加到对应的缓存中，数量+1
     *
     *      即最终注册到synchronizations对象的缓存中
     *
     *      缓存池使用的是一个ThreadLocal（用于处理多个线程中数据的隔离问题，内部维护一个ThreadLocalMap）来存储
     *
     *      synchronizations = new NamedThreadLocal<>(“Transaction synchronizations”);
     *
     * 总结：
     *      如果我们没有添加@Transactional注解，Spring认为我的每一次查询都都是相互独立的，便开启了三次不同的事务也即是创建了三个不同的sqlSession对象。即无法使用到MyBatis的一级缓存。
     *      如果我们添加了@Transactional注解，Spring在执行了第一次查询后，会将当前线程的事务情况存储到synchronizations 的集合中，当第二次再执行查询的时候，能够在缓存中直接获取到当前的事务情况（包含sqlSession对象），即不会再去调用openSession方法，继而创建一个新的sqlSession对象，而是使用缓存中的sqlSession对象。这就保证了在添加@Transactional注解的情况下，能够走MyBatis的一级缓存
     *
     */
}
