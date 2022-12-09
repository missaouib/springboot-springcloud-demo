package com.ruyuan.cloud.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 字段提冲所用的处理器
 *
 * @author zhonghuashishan
 **/
public class RuyuanMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时，更新公共的时间字段
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.strictInsertFill(metaObject, "gmtCreate", Date.class, date);
        this.strictInsertFill(metaObject, "gmtModified", Date.class, date);
    }

    /**
     * 更新时，只更新修改时间
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtModified", Date.class, new Date());
    }
}
