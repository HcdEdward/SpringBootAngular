package com.example.exception;

import com.example.vo.jsonVO.Result;
import com.example.vo.jsonVO.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huang.can.dong
 * @date 2018-10-30
 * */
@ControllerAdvice
public class MyControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        logger.info(ex.getMessage());
        return ResultGenerator.genFailResult(ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Result myErrorHandler(MyException ex) {
        logger.info("code:"+ex.getCode()+" "+"method:"+ex.getMethod());
        logger.info("msg:"+ex.getMessage());
        return ResultGenerator.genFailResult(ex.getMessage());
    }
}

