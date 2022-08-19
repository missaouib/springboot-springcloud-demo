package com.example.springbootspringclouddemo.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {

    private static String pattern;

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(pattern);
        }
    };

    public static Date convert(String source, String pattern) throws Exception {
        DateFormatThreadLocal.pattern = pattern;
        return df.get().parse(source);
    }

}
