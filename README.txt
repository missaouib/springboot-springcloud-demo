1、aop-aspect-order主要是研究单个切面不同aspect注解的执行顺序、多个切面的执行顺序
2、h2-mybatis主要是整合h2和mybatis，将数据存储在内存数据库中
3、tx-aspect-integration
    3.1 主要是整合TransactionInterceptor和aspect切面的执行顺序
    3.2 spring jdk动态代理，查找动态代理类的接口类型，见FindProxyClassTypePrint类，前提条件：在VM Options中添加参数-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
    3.3 spring cglib动态代理，前提条件：在VM Options中添加参数-Dcglib.debugLocation=E:/developer/IdeaProjects/springboot-springcloud-demo
4、mockito
该项目是模拟测试类，具体代码在mockito/src/test/java/com/example/mockito目录中
注意事项：在Mockito中打桩（即stub)有两种方法when(...).thenReturn(...)和doReturn(...).when(...)。这两个方法在大部分情况下都是可以相互替换的，但是在使用了Spies对象（@Spy注解），而不是mock对象（@Mock注解)的情况下他们调用的结果是不相同的（目前我只知道这一种情况，可能还有别的情形下是不能相互替换的）
5、i18n
5.1 DynamicResourceMessageSource类是动态监听文件变化
5.2 MessageFormatDemo类是MessageFormat的代码示例应用
6、full-project-integration项目是整合了controller、service、mapper简单流程的项目
7、mysql_xa项目根据mysql原始api实现了分布式事务，mysql客户端充当TM事务管理器的角色
8、druid-jta-atomikos项目整合了druid、jta、atomikos，实现了单系统跨多库的分布式事务
9、common-utils项目整合了常用的工具类
10、nio-demo项目演示了Java NIO的用法，包括ByteBuffer、channel、Selector
11、netty-demo项目整合了netty多个使用案例
    11.1 manual-rpc演示了手写rpc
    11.2 sticky项目演示了粘包拆包
       11.2.1 com.example.sticky.demo1目录的案例演示了粘包拆包现象
       11.2.2 针对demo1的粘包拆包现象，解决方案有：LineBasedFrameDecoder(行分隔解码器)、DelimiterBasedFrameDecoder(符号分隔解码器)、FixedLengthFrameDecoder(定长分隔解码器)
       11.2.3 com.example.sticky.demo2目录的案例解决了demo1目录的粘包拆包现象
12、websocket项目整合了与websocket相关的案例
    12.1 websocket-simple项目演示了websocket的简单应用
    12.2 websocket-push项目根据websocket-simple项目进行了应用，实现了push消息推送

13、kafka-demo项目演示了kafka生产和消费消息

14、rocketmq-demo项目演示了rocketmq生产和消费消息

15、docker-images-demo项目演示了通过docker插件构建镜像并推送到阿里云镜像服务

16、dockerfile-demo项目演示了通过编写Dockerfile文件构建镜像并推送到阿里云镜像服务

17、rabbitmq-demo项目演示了rabbitmq生产和消费消息

18、jmh-maven项目演示了如何使用jmh进行基准测试以及如何通过jar包启动

19、disruptor-demo项目使用disruptor框架演示了单生产者单消费者、演示了多生产者多消费者

20、ruyuan-cloud-starter项目自动装配了如下组件
kafka与rocketmq
Redis与ZK分布式锁，其中redis包括单机、主从、哨兵、集群
阿里云和腾讯云的短信接口
邮件发送
easypoi excel组件
elasticsearch和mybatis-plus


21、sentinel-sliding-window-demo项目演示了滑动时间窗算法的用法


22、gateway-demo项目主要是演示网关相关的功能
      spring-cloud-gateway-demo项目主要演示spring-cloud-gateway的功能
          cloud-eureka-server7001：作为服务注册中心
          cloud-provider-payment8001：作为服务提供者，可启动多个实例用于演示请求负载均衡
          cloud-gateway-gateway9527：作为网关路由


23、spring-cloud-alibaba-demo项目主要演示跟spring-cloud-alibaba相关的功能
      nacos-demo项目主要演示跟nacos相关的功能
          config-demo：作为配置中心
          discovery-provider-consumer：作为服务注册中心和服务发现中心
              consumer-demo：作为服务发现者
              provider-demo：作为服务提供者
          sentinel-nacos-demo：演示了sentinel的功能，并且和nacos进行了整合
      spring-cloud-stream-demo项目主要演示跟spring-cloud-stream相关的功能
          cloud-stream-rabbitmq-consumer8802：作为消息消费者
          cloud-stream-rabbitmq-provider8801：作为消息生产者


24、spring-webFlux-demo项目演示了spring-webFlux的基本使用



25、cache-demo项目演示了跟缓存相关的使用
      ehcache：ehcache的基本使用
      guava：guava cache的基本使用
      linkhashmap-softreference：基于LinkedHashMap和SoftReference实现LRU缓存淘汰
      linklist-lru：基于LinkedList实现LRU缓存淘汰
      caffeine：caffeine cache的基本使用


26、https-demo项目演示了如何使用spring boot搭建https网站


27、oauth2-demo项目演示了如何使用oauth2
      oauth2-redis：oauth2跟redis整合，仅限于password模式，也提供了注销token的方法
      spring-security-jwt-oauth2：spring-security、jwt、oauth2三方整合，提供了四种授权模式的基本使用
          springsecurity101-cloud-oauth2-server：授权服务器
          springsecurity101-cloud-oauth2-userservice：受保护资源
          springsecurity101-cloud-oauth2-client：第三方应用程序

    token 过期后，如何自动续期：https://cloud.tencent.com/developer/article/2001607


28、mysql-deadlock项目演示了mysql死锁的场景


29、kafka-canal项目演示了如何使用canal和kafka解析binlog日志


30、wechat-public-dailyhub项目演示了如何扫码登录微信公众号


31、tx-propagation项目演示了事务传播行为


32、java-concurrent项目演示了java并发相关的知识


33、jcstress-demo项目演示了如何使用jcstress进行压测


34、es-demo项目汇总了跟es有关的功能
     es-transportclient：通过transportclient访问es实现增删改查



35、apollo-demo项目演示了如何使用apollo动态修改配置


36、algorithm项目演示了数据结构与算法


37、only-mybatis-demo项目演示了如何仅应用mybatis查询数据库