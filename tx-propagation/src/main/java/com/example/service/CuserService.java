package com.example.service;

import com.example.model.Cuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

@Service
public class CuserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation= Propagation.REQUIRED)
    public void InsertRequiredCuser(Cuser cuser) {
        jdbcTemplate.update("INSERT INTO cuser(id,name, age, email) VALUES (?, ?, ?, ?);", cuser.getId(), cuser.getName(), cuser.getAge(), cuser.getEmail());
        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());
        int i = 1/0;
    }

    @Transactional(propagation= Propagation.NESTED)
    public void InsertNestedCuser(Cuser cuser) {
        jdbcTemplate.update("INSERT INTO cuser(id,name, age, email) VALUES (?, ?, ?, ?);", cuser.getId(), cuser.getName(), cuser.getAge(), cuser.getEmail());
        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());
    }
}