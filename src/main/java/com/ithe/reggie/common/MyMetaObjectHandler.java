package com.ithe.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
自定义元数据对象处理器
* */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /*
    插入操作自动填充
    * */
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("公共字段自动填充（insert）");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        long id = BaseContext.getCurrentId();//通过线程等方法实现
        log.info("线程id:{}",id);
        metaObject.setValue("createUser",id);
        metaObject.setValue("updateUser",id);
    }
    /*
    更新操作自动填充
    * */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]");
        log.info(metaObject.toString());

        long id = BaseContext.getCurrentId();
        log.info("线程id:{}",id);
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",id);
    }
}
