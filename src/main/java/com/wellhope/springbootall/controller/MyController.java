package com.wellhope.springbootall.controller;

import com.wellhope.springbootall.entity.Student;
import com.wellhope.springbootall.entity.TBook;
import com.wellhope.springbootall.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author GaoJ
 * @create 2021-02-26 12:49
 */
@Controller
@RequestMapping("my")
@Slf4j
public class MyController {

    @Autowired
    private BookService bookService;

    @Value("${resource.imageServer}")
    private String IMAGE_SERVER;

//    private static final Logger LOGGER  = LoggerFactory.getLogger(MyController.class);
    /**
     * 测试
     * @return
     */
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello,SpringBoot!";
    }

    //http://localhost:8080/springboot/my/showDate?date=2021-02-26
    /**
     * 测试日期类型
     * @return
     */
    @RequestMapping("showDate")
    @ResponseBody
    public Date showDate(Date date){
        System.out.println(date);
        return date;
    }
    /**
     * 测试自定义类型
     * @return
     */
    @RequestMapping("getImageServerpath")
    @ResponseBody
    public String getImageServerpath(){

        return IMAGE_SERVER;
    }

    /**
     * 测试mybatis
     * @param id
     * @return
     */
    //localhost:8080/springboot/my/getById/1
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public TBook getById(@PathVariable("id") Integer id){
        System.out.println(bookService.getById(id));
        return bookService.getById(id);
    }
    /**
     * 测试log
     * @return
     */
    @RequestMapping("log")
    @ResponseBody
    public String log(){
//        LOGGER.debug("debug......");
//        LOGGER.info("info......");
//        LOGGER.warn("warn......");
//        LOGGER.error("error.....");
//        LOGGER.info("你好[{}],你是第[{}]个登录网址的用户","huangguizhao","1");
        log.debug("调试级别的信息");
        log.info("普通级别的信息");
        log.warn("警告级别的信息");
        log.error("错误级别的信息");
        return "ok";
    }
    /**
     * 测试跳转html
     * @return
     */
    @RequestMapping("/showHello")
    public String showHello(Model model){
//       int i= 1/0;
        model.addAttribute("username","wy");
        model.addAttribute("age",30);
        List<Student> students=new ArrayList<>();
        students.add(new Student(1L,"wangwu"));
        students.add(new Student(2L,"zhaoliu"));
        model.addAttribute("students",students);
        model.addAttribute("now",new Date());
        return "showHello";
    }
}
