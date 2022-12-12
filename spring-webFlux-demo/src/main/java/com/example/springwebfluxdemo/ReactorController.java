package com.example.springwebfluxdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class ReactorController {

    @GetMapping("/1")
    private String get1() {
        log.info("get1 start");
        String result = createStr();
        log.info("get1 end");
        return result;
    }

    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some thing";
    }

    @GetMapping("/2")
    private Mono<String> get2() {
        log.info("get2 start");
        // 如果直接使用just方法那么它的线程耗时和get1结果一样，等到方法执行结束后才结束
        // Mono<String> result = Mono.just(createStr());
        // 注意需要使用流编程模式，惰性求值，实现异步
        Mono<String> result = Mono.fromSupplier(this::createStr);
        log.info("get2 end");
        return result;
    }

    /**
     * Flux 返回 1-N个元素
     * produces = "text/event-stream" 设置后像流一样返回数据，不设置就会一次全部返回
     * produces = MediaType.TEXT_EVENT_STREAM_VALUE：表示以SSE(Server-Sent Events)形式返回
     * @return
     */
//    @GetMapping(value = "/3", produces = "text/event-stream")
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> get3() {
        log.info("get3 start");
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "get3 flux data--" + i;
        }));
        log.info("get3 end");
        return result;
    }

    @GetMapping(value = "/4")
    private Flux<String> get4() {
        log.info("get4 start");
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "get4 flux data--" + i;
        }));
        log.info("get4 end");
        return result;
    }
}