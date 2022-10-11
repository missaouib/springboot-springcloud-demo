package com.example.jta.atomikos.service;

import com.example.jta.atomikos.domain.TxRequest;

/**
 * 抽奖机会service组件
 * @author zhonghuashishan
 *
 */
public interface LotteryDrawService {


	void increment(TxRequest txRequest);
	
}
