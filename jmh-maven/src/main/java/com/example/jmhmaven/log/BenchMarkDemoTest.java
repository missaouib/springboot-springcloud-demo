package com.example.jmhmaven.log;

import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;


//JMH测试类必须使用@State注解，State定义了一个类实例的生命周期，可以类比Spring Bean的Scope
@State(Scope.Benchmark)
@Fork(value = 1)
public class BenchMarkDemoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchMarkDemoTest.class);

    //Level.Trial：每个进程都会执行一次
    @Setup(Level.Trial)
    public void setUp(){
        ProcessIdUtils.logProcessId("setUp===");
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(1)
    //覆盖当前类的@Fork配置
    @Fork(value = 1)
    //预热的原因：当虚拟机发现某个方法或代码块的运行特别频繁时，就会把这些代码认定为热点代码。为了提高热点代码的执行效率，在运行时虚拟机将会把这些代码编译成与本地平台相关的机器码，并进行各种层次的优化
    @Warmup(iterations = 1,time = 1)
    @Measurement(iterations = 1,time = 10)
    public void testLog(BenchMarkDemoTest test) throws InterruptedException {
        //请求百度
        ResponseEntity<String> respJson = RestTemplateFactory.getInstance().create().getForEntity("http://www.baidu.com", String.class);
        LOGGER.info("百度返回结果：{}",respJson);
        ProcessIdUtils.logProcessId("testLog"+"====对象1==="+test.toString());
        Thread.sleep(10);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Threads(1)
    //预热的原因：当虚拟机发现某个方法或代码块的运行特别频繁时，就会把这些代码认定为热点代码。为了提高热点代码的执行效率，在运行时虚拟机将会把这些代码编译成与本地平台相关的机器码，并进行各种层次的优化
    @Warmup(iterations = 1,time = 1)
    @Measurement(iterations = 1,time = 1)
    public void testLog2(BenchMarkDemoTest test) throws InterruptedException {
        ProcessIdUtils.logProcessId("testLog2"+"====对象2==="+test.toString());
        Thread.sleep(10);
    }
//==============================本地通过main方法启动，一般不建议==============================
//    public static void main(String[] args) {
//        Options options = new OptionsBuilder()
//                .include(LogBenchMarkMain.class.getSimpleName())
//                .warmupIterations(1)
//                .warmupTime(TimeValue.seconds(1))
//                .measurementIterations(1)
//                .measurementTime(TimeValue.seconds(1))
//                .output("result.json")
//                .build();
//        try {
//            new Runner(options).run();
//        } catch (RunnerException e) {
//            e.printStackTrace();
//        }
//    }

}