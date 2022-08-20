package com.example.txaspect;

import com.example.txaspect.aop.AdviceAopOrderAspect;
import com.example.txaspect.controller.UserController;
import com.example.txaspect.model.User;
import com.example.txaspect.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication
@Configuration
//必须按顺序配置 DataSourceAutoConfiguration.class,MybatisLanguageDriverAutoConfiguration.class,MybatisAutoConfiguration.class
@Import({
        /** web相关 */ServletWebServerFactoryAutoConfiguration.class, DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, WebMvcAutoConfiguration.class,
        /** dataSource相关 */DataSourceAutoConfiguration.class,MybatisLanguageDriverAutoConfiguration.class,MybatisAutoConfiguration.class,
        /** 事务相关 */DataSourceTransactionManagerAutoConfiguration.class, TransactionAutoConfiguration.class,
        /** 自定义组件 */UserController.class,UserService.class, AdviceAopOrderAspect.class
})
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
//加载配置文件
@PropertySource("application.properties")
//加载mapper文件
@MapperScan("com.example.txaspect.mapper")
public class TxAspectIntegrationApplication {

    public static void main(String[] args) throws Exception {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext();
        context.register(TxAspectIntegrationApplication.class);

        context.refresh();

        ServletWebServerFactory bean = context.getBean(ServletWebServerFactory.class);

        UserController controller = context.getBean(UserController.class);
        System.out.println(controller.getUser(new User()));

        //将程序阻塞
        System.in.read();

        context.close();
    }
}
