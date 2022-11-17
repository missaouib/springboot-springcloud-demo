package com.example.manualrpc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {

    private static final Logger logger = LogManager.getLogger(Log4j2Test.class);

    public static void main(String[] args) {
        logger.info("hello log4j2......");
    }

}
