package com.zhm.config;


import com.zhm.constant.Constant;
import com.zhm.dto.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * Created by 赵红明 on 2019/11/18.
 */
@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception exception){
      return Result.sendFailure("系统异常",exception.getMessage());
    }
}
