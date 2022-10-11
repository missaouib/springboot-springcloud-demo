package com.example.jta.atomikos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 流量充值中心启动类
 * @author zhonghuashishan
 *
 */
@SpringBootApplication
@ServletComponentScan
public class TxApplication {
	
	public static void main(String[] args) { 
		SpringApplication.run(TxApplication.class, args);
	}

}
