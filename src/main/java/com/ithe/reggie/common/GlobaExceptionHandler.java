package com.ithe.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/*
全局异常处理
* */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobaExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//处理这种sql异常
    public R<String> exceptionhandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = "失败了!"+split[2]+"账户重复";
            return R.error(msg);
        }
        return R.error("未知错误！");
    }
    @ExceptionHandler(CustomException.class)//处理这种自定义菜品/套餐异常
    public R<String> exceptionhandler(CustomException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}
