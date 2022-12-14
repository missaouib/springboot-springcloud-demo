package com.example.linkhashmapsoftreference;

public class CacheTest {

    public static void main(String[] args) throws Exception {
        //测试LRU缓存淘汰
        //test1();
        //测试软引用内存敏感 gc参数设置：-Xmx20m -Xms20m -XX:+PrintGCDetails
        test2();
    }


    public static void test1() {
        CacheVersion1 cache = new CacheVersion1(3);
        cache.put("a","a_value");
        cache.put("b","b_value");
        cache.put("c","c_value");
        System.out.println(cache);
        //演示访问一次，改变排序
        String bValue = (String)cache.get("b");
        System.out.println("b的值："+bValue);
        //重新排序
        System.out.println(cache);
        //LRU演示
        cache.put("d","d_value");
        System.out.println(cache);
    }

    /**
     * 测试软引用内存敏感
     * 强引用：只要有引用就不会被回收，基本OOM
     * 软引用：gc且堆内存吃紧时才会被回收(适合)
     * 弱引用：每次gc都会被回收
     * 虚引用：随时都有可能被回收
     * @throws InterruptedException
     */
    public static void test2() throws InterruptedException {
        CacheVersion1 cache = new CacheVersion1(99999);
        for (int i = 1; i < 50; i++) {
            System.out.println("放入第"+i+"个");
            Dept dept = new Dept((long)i);
            cache.put(dept.getId(),dept);
        }
    }

}
