package com.example.mockito;

import com.example.mockito.controller.UserController;
import com.example.mockito.model.User;
import com.example.mockito.service.UserService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockUserServiceTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService  userService;

    @Test
    public void mockUserService() {
        User mockUser = new User(2L,"mockUser");
        Mockito.doReturn(mockUser).when(userService).getUser(Mockito.any());
        User user = userController.getUser(new User());
        MatcherAssert.assertThat(user,CoreMatchers.equalTo(mockUser));
    }

}
