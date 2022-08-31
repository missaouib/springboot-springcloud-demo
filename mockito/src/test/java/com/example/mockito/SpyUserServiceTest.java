package com.example.mockito;

import com.example.mockito.controller.UserController;
import com.example.mockito.mapper.UserMapper;
import com.example.mockito.model.User;
import com.example.mockito.service.ShopService;
import com.example.mockito.service.UserService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpyUserServiceTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserController userController;

    @SpyBean
    private UserService userService;

    @SpyBean
    private ShopService shopService;

    @Before
    public void setup() {
        //级联注入属性
        this.cascadeInjectField(this.getClass());
    }

    private void cascadeInjectField(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredFields()).forEach(field->{
            SpyBean spyBean = field.getAnnotation(SpyBean.class);
            if (Objects.nonNull(spyBean)) {
                Object spyObj = context.getBean(field.getType());
                if (Objects.nonNull(spyObj)) {
                    Arrays.stream(field.getType().getDeclaredFields()).forEach(innerField->{
                        //标注@Autowired注解的属性，暂时忽略@Resource等注解的处理
                        Autowired autowired = innerField.getAnnotation(Autowired.class);
                        if (Objects.nonNull(autowired)) {
                            Object bean = context.getBean(innerField.getType());
                            innerField.setAccessible(true);
                            try {
                                innerField.set(spyObj,bean);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //标注@Value注解的属性 TODO
                            //此种情况不处理，在具体的测试方法中根据需要通过反射赋值
                        }
                    });
                }
            }
        });
    }


    @Test
    public void spyUserService() {
        ShopService shopService = new ShopService("newShop...");
        User user1 = userController.getUser(new User());
        Mockito.doReturn(shopService).when(userService).getShopService();
        User user2 = userController.getUser(new User());
        System.out.println(user2);
    }

}
