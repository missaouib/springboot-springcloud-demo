package com.example.jta.atomikos.service.impl;

import com.example.jta.atomikos.domain.TxRequest;
import com.example.jta.atomikos.service.LotteryDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jta.atomikos.mapper.lottery.LotteryDrawMapper;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 抽奖机会service组件
 * @author zhonghuashishan
 *
 */
@Service
public class LotteryDrawServiceImpl implements LotteryDrawService {

	/**
	 * 抽奖机会mapper组件
	 */
	@Autowired
	private LotteryDrawMapper lotteryDrawMapper;

	public void increment(TxRequest txRequest) {
		lotteryDrawMapper.increment(1L);
		if (Objects.equals(txRequest.getLotteryError(),true)) {
			int i = 1/0;
		}
	}
	
}
