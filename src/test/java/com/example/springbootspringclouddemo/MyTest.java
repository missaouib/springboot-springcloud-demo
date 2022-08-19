package com.example.springbootspringclouddemo;

import com.example.springbootspringclouddemo.pojo.Teacher;
import com.google.common.collect.Maps;
import com.netflix.hystrix.*;
import lombok.Data;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class MyTest {

    public MyTest() {
    }

    public static void main(String[] args) throws InterruptedException {
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        Map<String,Object> map = Maps.newHashMap();
        map.put("name","zhangsan");
        map.put("age",18);
        byte[] serialize = serializer.serialize(map);
        System.out.println(Arrays.toString(serialize));
        System.out.println(new BigInteger(1, serialize).toString(16));
        System.out.println(Arrays.toString(toHexString("我爱你").getBytes(StandardCharsets.UTF_8)));
        System.out.println(Arrays.toString("我爱你".getBytes(StandardCharsets.UTF_8)));
        System.out.println(Integer.toString(-26, 16));


    }

    //转化字符串为十六进制编码
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            System.out.println(s4);
            str = str + s4;
        }
        return str;
    }

    public static void change(Integer n) {
        n += 1;
    }
}

@Data
class MyInt {

    private int i;

    public MyInt (int i) {
        this.i=i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyInt myInt = (MyInt) o;

           return i == myInt.i;

    }

    @Override
    public int hashCode() {
        if (this.i==1 || this.i == 11) {
            return 1;
        }
        return this.i;
    }
}

