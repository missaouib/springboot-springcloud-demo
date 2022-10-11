package com.example.jta.atomikos.web;


import com.example.jta.atomikos.service.TxService;
import com.example.jta.atomikos.domain.TxRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller组件
 * @author zhonghuashishan
 *
 */
@RestController
@RequestMapping("/jta/atomikos")
public class TxController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TxController.class);

	@Autowired
	private TxService txService;

	@RequestMapping("/tx")
	public String tx(@RequestBody TxRequest txRequest) {
		String result = "执行成功！";
		try {
			// 执行分布式事务
			txService.txLogic(txRequest);
		} catch (Exception e) {
			LOGGER.error("出现异常:",e);
			result = "执行失败！";
		}
		return result;
	}
	
}
