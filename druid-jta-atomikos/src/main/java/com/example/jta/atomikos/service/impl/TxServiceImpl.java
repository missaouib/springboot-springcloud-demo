package com.example.jta.atomikos.service.impl;

import com.example.jta.atomikos.service.CreditService;
import com.example.jta.atomikos.service.LotteryDrawService;
import com.example.jta.atomikos.domain.TxRequest;
import com.example.jta.atomikos.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "xatx",rollbackFor = Exception.class)
public class TxServiceImpl implements TxService {

	/**
	 * 抽奖机会service组件
	 */
	@Autowired
	private LotteryDrawService lotteryDrawService;

	/**
	 * 积分service组件
	 */
	@Autowired
	private CreditService creditService;


	public void txLogic(TxRequest txRequest) {
		// 给用户增加一次抽奖机会
		lotteryDrawService.increment(txRequest);
		// 给用户增加充值面值5%的积分
		creditService.increment(txRequest);
	}
	
}
