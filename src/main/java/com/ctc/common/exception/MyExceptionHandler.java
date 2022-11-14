package com.ctc.common.exception;

import com.ctc.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-07 18:08
 * @version:1.0
 **/
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> myExceptionHandler(Exception e){
        return Result.fail ( "系统错误，错误信息：" + e.getMessage () );
    }
}
