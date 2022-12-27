package com.example.service;

import com.example.model.Cuser;
import com.example.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CuserService cuserService;

    @Transactional(propagation= Propagation.REQUIRED)
    public void InsertRequiredAndRequiredUsers(Users users) {
        jdbcTemplate.update("INSERT INTO users(id,name, age, email) VALUES (?, ?, ?, ?);",users.getId(), users.getName(), users.getAge(), users.getEmail());
        //调用service中另一个方法
        Cuser cuser = new Cuser(users.getId(), users.getName(), users.getAge(), users.getEmail());
        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());
        //此处加try/catch的目的：为了杜绝InsertRequiredCuser中抛出的异常影响InsertRequiredAndRequiredUsers方法的实验结果
        try {
            cuserService.InsertRequiredCuser(cuser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void InsertRequiredAndNestedUsers(Users users) {
        jdbcTemplate.update("INSERT INTO users(id,name, age, email) VALUES (?, ?, ?, ?);",users.getId(), users.getName(), users.getAge(), users.getEmail());
        //调用service中另一个方法
        Cuser cuser = new Cuser(users.getId(), users.getName(), users.getAge(), users.getEmail());
        //打印事务名
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT TRX_ID FROM INFORMATION_SCHEMA.INNODB_TRX WHERE TRX_MYSQL_THREAD_ID = CONNECTION_ID( );");
        System.out.println(maps + TransactionSynchronizationManager.getCurrentTransactionName());
        try {
            cuserService.InsertNestedCuser(cuser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 1/0;
    }

}