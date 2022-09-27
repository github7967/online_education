package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiSong
 * @since 2022-09-19
 */
@ControllerAdvice
@Slf4j
public class GlobaExceptionHandler {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("internal error...");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.error().message("Calculation error...");
    }

    //自定义异常处理(不会自动抛出,需要手动抛出)
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
