package com.example.onlymybatisdemo;

import com.example.onlymybatisdemo.mapper.UserMapper;
import com.example.onlymybatisdemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class MybatisTest {

    public static void main(String[] args) throws Exception {
        test1();
        System.out.println("===========================================================================");
        test2();
    }

    /**
     * 方式一
     * @throws Exception
     */
    public static void test1() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("com.example.onlymybatisdemo.mapper.UserMapper.selectUser", 1);
        log.info("test1 user:{}",user);
    }

    /**
     * 方式二
     * @throws Exception
     */
    public static void test2() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //指定执行器类型
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUser(1);//查出来的结果是一样
        log.info("test2 user:{}",user);
    }

}
