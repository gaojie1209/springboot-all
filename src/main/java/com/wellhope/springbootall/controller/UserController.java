package com.wellhope.springbootall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellhope.springbootall.config.Resource;
import com.wellhope.springbootall.entity.User;
import com.wellhope.springbootall.pojo.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GaoJ
 * @create 2021-02-26 22:34
 */
@RestController
@RequestMapping("user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private Resource resource;

    //  @RequestMapping(value="hello",method = RequestMethod.GET)
    //restful 架构风格
    @GetMapping("{id}")
    public User getById(@PathVariable("id") Long id){
        logger.info("id"+id);
        logger.info("查询操作");
        return new User(id,"wy",new Date());
    }
    @DeleteMapping("{id}")
    public User delById(@PathVariable("id") Long id){
        logger.info("id"+id);
        logger.info("删除操作");
        return new User(id,"wy",new Date());
    }
    @RequestMapping("getImageServerpath")
    @ResponseBody
    public String getImageServerpath(){

        return resource.getImageServer();
    }
//    @PostMapping("add")
//    public ResultBean add(@Valid User user, BindingResult bindingResult) throws JsonProcessingException {
//        //判断数据校验是否通过
//        if(bindingResult.hasErrors()){
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            //保存错误信息的map
//            Map<String,String> resultMap=new HashMap<>();
//            for (FieldError fieldError : fieldErrors) {
//                String message = fieldError.getDefaultMessage();
//                String field = fieldError.getField();
//                //这样就可以罗列出有问题的字段和错误提示信息
//                logger.error("[{}]:[{}]",field , message);
//                resultMap.put(field,message);
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            //将对象转为json
//            String result = objectMapper.writeValueAsString(resultMap);
//            return new ResultBean("400",result);
//        }
//        return new ResultBean("200","ok");
//    }
    @PostMapping("add")
    public ResultBean add(@Valid User user) {
        //AOP 将核心业务逻辑和非核心业务逻辑做分离
        //1核心业务逻辑：做用户数据的添加
        //2非核心业务逻辑：做数据格式的校验
        logger.info("模拟实现了用户的添加");
        return new ResultBean("200","ok");
    }
}
