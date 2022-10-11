package com.example.jta.atomikos.service.impl;

import com.example.jta.atomikos.domain.TxRequest;
import com.example.jta.atomikos.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jta.atomikos.mapper.credit.CreditMapper;

import java.util.Objects;

/**
 * 积分service组件
 * @author zhonghuashishan
 *
 */
@Service
public class CreditServiceImpl implements CreditService {

	/**
	 * 积分mapper组件
	 */
	@Autowired
	private CreditMapper creditMapper;

	public void increment(TxRequest txRequest) {
		creditMapper.increment(1L,5.0D);
		if (Objects.equals(txRequest.getCreditError(),true)) {
			int i = 1/0;
		}
	}
	
}
