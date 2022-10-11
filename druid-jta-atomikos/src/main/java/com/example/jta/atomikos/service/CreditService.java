package com.example.jta.atomikos.service;

import com.example.jta.atomikos.domain.TxRequest;

/**
 * 积分service组件
 * @author zhonghuashishan
 *
 */
public interface CreditService {


	void increment(TxRequest txRequest);
	
}
