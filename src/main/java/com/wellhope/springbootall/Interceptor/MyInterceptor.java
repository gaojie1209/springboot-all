package com.wellhope.springbootall.Interceptor;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * jdk1.8之后，接口可以有默认的实现，所以不再需要一个类来做中间的适配处理
 * @author GaoJ
 * @create 2021-02-26 20:38
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("拦截器");
//        return true;
//    }
}
