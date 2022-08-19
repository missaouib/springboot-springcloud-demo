package com.example.springbootspringclouddemo;

import com.example.springbootspringclouddemo.pojo.DateFormatThreadLocal;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;

public class TestSimpleDateFormat {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20191228", formatter);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }
        for (Future<LocalDate> result : results) {
            System.out.println(result.get());
        }
        pool.shutdown();
    }

    @Test
    public void test01() {
        Instant instant = Instant.now();//默认是UTC时区
        System.out.println(instant);
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        //转成毫秒值输出
        System.out.println(instant.toEpochMilli());
        //自定义instant
        Instant instant1 = Instant.ofEpochSecond(1000);
        System.out.println(instant1);
    }

    @Test
    public void test02() {
        Instant instant = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant, instant1);
        //获取毫秒值
        System.out.println(duration.toMillis());
        System.out.println("===================================");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1, lt2).toMillis());
    }

    @Test
    public void test03() {
        LocalDate ld1 = LocalDate.of(2015, 1, 1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    @Test
    public void test04() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        //使用with方法也可以实现
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);
        //使用TemporalAdjuster调整时间
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);
        //自定义：获取下一个工作日
        LocalDateTime ldt5 = ldt.with(l -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    @Test
    public void test06() {
        //获取所有可用的时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
        A a = new A();

    }

    public class A<T extends A> {
        private T t;

        public void set(T t) {
            this.t = t;
        }

        public void show(A<?> t) {
            this.t = (T) t;
            this.t.set(new A());

        }
    }
}
