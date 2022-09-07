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