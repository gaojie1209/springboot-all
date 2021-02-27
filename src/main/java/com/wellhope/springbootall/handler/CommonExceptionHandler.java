package com.wellhope.springbootall.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellhope.springbootall.pojo.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理类
 *
 * @author GaoJ
 * @create 2021-02-27 11:23
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private static final Logger LOGGER  = LoggerFactory.getLogger(CommonExceptionHandler.class);
    /**
     * 做一个全局的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean handleException(Exception e){
        //1将错误日志记录下来
        LOGGER.error(e.getMessage());
        //2给用户反馈
        return new ResultBean("success","当前服务器繁忙，请稍后再试");
//        return new ResultBean("500",e.getMessage());
    }

    /**
     * 数据校验异常，做定制的处理机制
     * @param ex
     * @return
     * @throws JsonProcessingException
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultBean validationErrorHandler(BindException ex) throws JsonProcessingException {
        //1.此处先获取BindingResult
        BindingResult bindingResult = ex.getBindingResult();
        //2.获取错误信息
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        //3.组装异常信息
        Map<String,String> message = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            message.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        //4.将map转换为JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(message);
        //5.返回错误信息
        return new ResultBean("400",json);
    }
}
