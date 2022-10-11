package com.example.jta.atomikos.service;

import com.example.jta.atomikos.domain.TxRequest;


public interface TxService {

	void txLogic(TxRequest txRequest);
	
}
