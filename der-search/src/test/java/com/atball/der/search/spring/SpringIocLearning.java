package com.atball.der.search.spring;


import com.atball.der.search.DerSearchApplicationTests;
import com.atball.der.search.config.MainConfig;
import com.atball.der.search.dto.Car;
import org.apache.naming.factory.BeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpringIocLearning extends DerSearchApplicationTests {
    /**
     * Spring 源码整体脉络介绍及源码编译
     * BeanDefinition：
     *      检查bd，一个Bean的描述
     * AnnotatedBeanDefinition：
     *      BeanDefinition的子接口，描述加了注解的类
     * RootBeanDefinition：
     *      BeanDefinition的子类，描述Spring内部提供的类
     * BeanDefinitionHolder：
     *      简称bdh，一个Map方便传参
     *
     * BeanDefinitionRegistry：
     *      是一个bd的一个注册器
     * DefaultListableBeanFactory：
     *
     * AnnotatedBeanDefinitionReader:
     *     bd读取器，给一个类给他，他帮你转换成bd，但是这个对象只能读取加了注解的类
     * ClassPathBeanDefinitionScanner
     *     能够扫描我们的bd，能够扫描一个类，并且转换成bd
     *
     * beanDefinitionNames：
     *     是一个List存放所有的beanName
     * beanDefinitionMap：
     *      是一个Map，里面存的是bean的名字和bean的描述
     *
     *
     *
     *
     *  描述一下BeanFactory
     *      // 加载spring上下文
     *      Spring 顶层核心接口，使用了简答工厂模式，负责生产Bean
     *
     *      BeanDefinitionScanner
     *      BeanDefinitionReader
     *      BeanDefinitionRegister --> BeanDefinition
     *      实例化（反射） ——> 填充属性 ——> 初始化 （初始化后postProcessor实现Aop）
     *
     *      BeanFactory和context的区别
     *        共：都有生产Bean的能力 区别：context有扫描器，注册器，BeanFactory就是Bean的生产机器，BeanFactory只能一个一个塞Bean定义
     *
     *   实例化方式 1、工厂 （自己可以new 更加的灵活）2、反射（Spring控制）
     *             BeanFactoryPostProcessor --> 修改Bean定义
     *                  void postProcessBeanFactory(ConfigurableListableBeanFactory var1) throws BeansException;
     *             BeanFactoryPostProcessor子接口BeanDefinitionRegistryPostProcessor --> 注册（添加）
     *                  void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry var1) throws BeansException;
     */

    /**
     *  BeanFactory 和 FactoryBean 的区别
     *     BeanFactory 是一个工厂
     *     继承FactoryBean的类 getBean("&BeanName")获取原有对象，getBean("BeanName")获取的是FactoryBean中getObject被重写后返回的对象
     *
     *
     */

    // depends-on
    /**
     * 类加载顺序
     */
    //
    /**
     *  Ioc 荣容器加载过程
     *
     *      public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
     *
     *              // super(beanFactory) 先调用父类的无参构造方法 this.beanFactory = new DefaultListableBeanFactory();
     *              // DefaultListableBeanFactory实现了BeanDefinitionRegistry接口 ——> 可以注册Bean定义
     *          super(beanFactory);
     *
     *              // AnnotatedBeanDefinitionReader里面很重要的的一点是往beanDefinitionMap中塞入了很多bean定义信息（创世类）
     *              // 其中包括:
     *              // 1、internalConfigurationAnnotationProcessor --> ConfigurationClassPostProcessor
     *              // (在这个类中，会解析加了@Configuration的配置类，还会解析@ComponentScan、@ComponentScans注解扫描的包，以及解析@Import等注解。)
     *         this.reader = new AnnotatedBeanDefinitionReader(this);
     *
     *              //扫描器
     *              //会加载系统环境变量和资源读取器
     *              //并且里面定义了扫描包的核心方法doScan().
     *              //但是这里的scanner只用于applicationContext调用调度扫描包注册beanDefinition
     *              //还有个非常重要的地方，new ClassPathBeanDefinitionScanner的时候会调用registerDefaultFilters();在里面注册扫描时过滤候选component的includeFilters
     *         this.scanner = new ClassPathBeanDefinitionScanner(this);
     *      }
     *
     *
     *  refresh()方法
     *
     *
     *     // 第四:留个子类去实现该接口
     *     postProcessBeanFactory(beanFactory);
     *
     *     // 调用我们的bean工厂的后置处理器. 1. 会在此将class扫描成beanDefinition  2.bean工厂的后置处理器调用
     *     invokeBeanFactoryPostProcessors(beanFactory);
     *
     *     // 注册我们bean的后置处理器
     *     registerBeanPostProcessors(beanFactory);
     *
     *     // 初始化国际化资源处理器.
     *     initMessageSource();
     *
     *     // 创建事件多播器
     *     initApplicationEventMulticaster();
     *
     *     // 这个方法同样也是留个子类实现的springboot也是从这个方法进行启动tomcat的.
     *     onRefresh();
     *
     *     //把我们的事件监听器注册到多播器上
     *     registerListeners();
     *
     *     // 实例化我们剩余的单实例bean.
     *     finishBeanFactoryInitialization(beanFactory);
     *
     *     // 最后容器刷新 发布刷新事件(Spring cloud也是从这里启动的)
     *     finishRefresh();
     *
     *
     */

    // 五种实例化bean的方式：
    /**
     * 1、现InstantiationAwareBeanPostProcessor接口
     * 2、实现FactoryBean接口
     * 3、通过Supplier接口
     * 4、工厂方法factory-method
     * 5、通过反射
     */

    // 内置后置处理器PostProcessor处理器深度解析（分为Bean工厂的PostProcessor和Bean的PostProcessor）
    // BeanFactoryPostProcessor调用过程


    // this.finishBeanFactoryInitialization(beanFactory);

    /**
     * protected Object getSingleton(String beanName, boolean allowEarlyReference) {
     *      Object singletonObject = this.singletonObjects.get(beanName);
     *          if (singletonObject == null && this.isSingletonCurrentlyInCreation(beanName)) {
     *              synchronized(this.singletonObjects) {
     *                  singletonObject = this.earlySingletonObjects.get(beanName);
     *                  if (singletonObject == null && allowEarlyReference) {
     *                      ObjectFactory<?> singletonFactory = (ObjectFactory)this.singletonFactories.get(beanName);
     *                      if (singletonFactory != null) {
     *                          singletonObject = singletonFactory.getObject();
     *                          this.earlySingletonObjects.put(beanName, singletonObject);
     *                          this.singletonFactories.remove(beanName);
     *                      }
     *                  }
     *              }
     *          }
     *
     *      return singletonObject;
     * }
     *
     */

    // 一级缓存: 完整的Bean
    /**
     *  public static Map<String,Object> singletonObject = new ConcurrentHashMap<>();
     */

    // 二级缓存: 为了将成熟Bean和纯净Bean分离，避免读取到不完整的Bean
    /**
     *  public static Map<String,Object> earlySingletonObjects = new ConcurrentHashMap<>();
     */

    // 三级缓存: 单例对象工厂的cache，存放 bean 工厂对象，用于解决循环依赖
    /**
     * private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16); // 三级缓存
     */

     // Spring 创建Bean在什么时候创建动态代理（分情况）
    /**
     *  1、出现循环依赖，实例化之后创建
     *
     *  2、正常情况下，在初始化之后创建
     *
     */

    // singleton Spring可以解决循环依赖 prototype(原型模式) 不可以


    // spirng refresh流程
    /**
     *  //实例化一个工厂DefaultListableBeanFactory
     * org.springframework.context.support.GenericApplicationContext->GenericApplicationContext()
     *   	1、实力化一个AnnotatedBeanDefinitionReader
     * 	2、ClassPathBeanDefinitionScanner，能够扫描我们bd,能够扫描一个类，并且转换成bd
     * 	org.springframework.context.annotation.AnnotationConfigApplicationContext#AnnotationConfigApplicationContext()
     * 		委托AnnotationConfigUtils
     * 		org.springframework.context.annotation.AnnotatedBeanDefinitionReader#AnnotatedBeanDefinitionReader()
     *
     * 			org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors()
     *
     * 				1、添加AnnotationAwareOrderComparator类的对象，主要去排序
     * 				2、ContextAnnotationAutowireCandidateResolver
     * 				3、往BeanDefinitionMap注册一个ConfigurationClassPostProcessor?  org.springframework.context.annotation.internalConfigurationAnnotationProcessor
     * 					why?因为需要在invokeBeanFactoryPostProcessors
     * 					invokeBeanFactoryPostProcessors主要是在spring的beanFactory初始化的过程中去做一些事情，怎么来做这些事情呢？
     * 					委托了多个实现了BeanDefinitionRegistryPostProcessor或者BeanFactoryProcessor接口的类来做这些事情,有自定义的也有spring内部的
     * 					其中ConfigurationClassPostProcessor就是一个spring内部的BeanDefinitionRegistryPostProcessor
     * 					因为如果你不添加这里就没有办法委托ConfigurationClassPostProcessor做一些功能
     * 					到底哪些功能？参考下面的注释
     * 				4、RequiredAnnotationBeanPostProcessor
     * 				.......
     * 				org.springframework.context.annotation.AnnotationConfigUtils#registerAnnotationConfigProcessors()
     * 					//往BeanDefinitionMap注册
     * 					org.springframework.context.annotation.AnnotationConfigUtils#registerPostProcessor
     * 						//准备好bean工厂，实例化对象
     * 						org.springframework.context.support.AbstractApplicationContext#refresh
     * 						//准备工作包括设置启动时间，是否激活标识位， 初始化属性源(property source)配置
     * 							org.springframework.context.support.AbstractApplicationContext#prepareRefresh
     * 								//得到beanFactory?因为需要对beanFactory进行设置
     * 								org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory
     * 									//准备bean工厂
     * 									1、添加一个类加载器
     * 									2、添加bean表达式解释器，为了能够让我们的beanFactory去解析bean表达式
     * 									3、添加一个后置处理器ApplicationContextAwareProcessor
     * 									4、添加了自动注入别忽略的列表
     * 									5、。。。。。。
     * 									6、添加了一个ApplicationListenerDetector后置处理器（自行百度）
     * 									org.springframework.context.support.AbstractApplicationContext#prepareBeanFactory
     * 										目前没有任何实现
     * 										org.springframework.context.support.AbstractApplicationContext#postProcessBeanFactory
     * 											1、getBeanFactoryPostProcessors()得到自己定义的（就是程序员自己写的，并且没有交给spring管理，就是没有加上@Component）
     * 											2、得到spring内部自己维护的BeanDefinitionRegistryPostProcessor
     * 											org.springframework.context.support.AbstractApplicationContext#invokeBeanFactoryPostProcessors
     * 												//调用这个方法
     * 												//循环所有的BeanDefinitionRegistryPostProcessor
     * 												//该方法内部postProcessor.postProcessBeanDefinitionRegistry
     * 												org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanDefinitionRegistryPostProcessors
     * 													//调用扩展方法postProcessBeanDefinitionRegistry
     * 													org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry
     * 														//拿出的所有bd，然后判断bd时候包含了@Configuration、@Import，@Compent。。。注解
     * 														org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions
     * 															1、的到bd当中描述的类的元数据（类的信息）
     * 															2、判断是不是加了@Configuration   metadata.isAnnotated(Configuration.class.getName())
     * 															3、如果加了@Configuration，添加到一个set当中,把这个set传给下面的方法去解析
     * 															org.springframework.context.annotation.ConfigurationClassUtils#checkConfigurationClassCandidate
     * 															//扫描包
     *
     * 															org.springframework.context.annotation.ConfigurationClassParser#parse(java.util.Set<org.springframework.beans.factory.config.BeanDefinitionHolder>)
     *
     * 																org.springframework.context.annotation.ConfigurationClassParser#parse(org.springframework.core.type.AnnotationMetadata, java.lang.String)
     * 																	//就行了一个类型封装
     * 																	org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass
     * 																	1、处理内部类 一般不会写内部类
     * 																	org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass
     * 																		//解析扫描的一些基本信息，比如是否过滤，比如是否加入新的包。。。。。
     * 																		org.springframework.context.annotation.ComponentScanAnnotationParser#parse
     * 																			org.springframework.context.annotation.ClassPathBeanDefinitionScanner#doScan
     * 																			org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#findCandidateComponents
     * 																				org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#scanCandidateComponents
     *
     */


    // 给Spring注册一个类有几种方法

    /**
     * register() baMap.put() 需要一个类 没办法参与类变成bd过程
     * scan()
     * ImportBeanDefinitionRegistrar
     */

    // @Import(value = "") 三种值

    /**
     * 普通类
     * ImportSelector配合Enable注解达到开关的作用
     * ImportBeanDefinitionRegistrar
     */

    // Spring四种类

    /**
     * 普通类 --》扫描完成之后就注册
     * importSelector
     * registrar
     * import普通类
     */

    // @Configuration的作用

    /**
     * 配置类加上@Configuration会产生代理
     */


    @Test
    public void test() {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Car car = (Car) context.getBean("car");
        System.out.println(car.getName());

    }

    // BeanPostProcessor
    /**
     * 插手Bean的实例化过程，在bean没有被Spring的bean容器管理之前干活
     * 经典场景：1、@PostConstruct，2、Aop
     */
    // BeanFactoryPostProcessor
    /**
     * Spring Bean容器当中任意一个Bean被new出来之前执行
     * 针对BeanFactory来建设
     * 经典应用场景：ConfigurationClassPostProcessor#postProcessBeanFactory（针对配置类加上cglib代理）
     */
    // BeanDefinitionRegistryPostProcessor
    /**
     * 是BeanFactoryPostProcessor的子类在BeanFactoryPostProcessor之前执行 因为源码当中先遍历BeanDefinitionRegistryPostProcessor
     * （有Spring提供的，还有自定义）自定义先执行，ConfigurationClassPostProcessor扫描，三种import的扫描 @Bean的扫描，
     * 判断配置类是否是一个完整的配置类
     */
    // importSelector
    /**
     * 通过这个方法selectorImport返回一个类名（全名），把它变成bd动态添加bd（这个bd是死的->class变成bd是Spring给我们做的）
     * 也可以动态扫描
     */
    // re



    // ImportAwareBeanPostProcessor
    /**
     * ImportAwareBeanPostProcessor是ConfigurationClassPostProcessor中的内部类
     * 实现ImportAware接口中的方法可以得到任意注解上的注解值
     */

    // CommonAnnotationBeanPostProcessor
    /**
     * 处理@PostContruct和@PreDestroy
     */

    // AutowiredAnnotationBeanPostProcessor
    /**
     * 处理@Autowired注解
     */

    // RequiredAnnotationBeanPostProcessor
    /**
     * 处理@Requested
     */


    // Spring 自动装配模型不等于自动装配技术
    // 自动装配mode == no 需要set方法，另外属性是Class类型的会过滤，不进行自动装配 使用by type的装配技术 就不需要在属性上加@Autowired
    // Spring默认No


}


