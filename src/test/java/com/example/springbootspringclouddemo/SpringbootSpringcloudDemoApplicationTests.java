package com.example.springbootspringclouddemo;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringbootSpringcloudDemoApplicationTests {

    @Test
    public void contextLoads() {
        List<Integer> list = Arrays.asList(1000, 1100, 1300, 1600, 2000, 2500);
        List<Integer> increaseList = Lists.newArrayList();
        for (int i = 1; i < list.size(); i++) {
            increaseList.add(list.get(i) - list.get(i - 1));
        }
        System.out.println(increaseList);
    }

    @Test
    public void test01() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println(timestamp);
    }

}
