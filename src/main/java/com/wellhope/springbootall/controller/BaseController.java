package com.wellhope.springbootall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public abstract class BaseController<T> {

    private Class<T> clazz = null;
    {
        try {
            //获取当前BaseDAO的子类继承的父类中的泛型
            Type genericSuperclass = this.getClass().getGenericSuperclass();
            ParameterizedType paramType = (ParameterizedType) genericSuperclass;

            Type[] typeArguments = paramType.getActualTypeArguments();//获取了父类的泛型参数
            clazz = (Class<T>) typeArguments[0];//泛型的第一个参数
//            T t = clazz.newInstance();
            final Logger LOGGER  = LoggerFactory.getLogger(clazz.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private MessageSource messageSource;
//
//    @Autowired
//    private CompanyService companyService;

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;

    }

    /**
     * 获取访问者ip
     * @return
     */
    public String getIpAddr() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 获取访问者用户信息
     * @return
     */
//    protected User getUser() {
//        HttpServletRequest req = this.getRequest();
//        User user = (User)req.getAttribute("user");
//        if (null == user) {
//            throw new DefaultException(401, "exception.user.isnull");
//        }
//        Company company ;
//        Long companyId = user.getCompanyId();
//        if (null == companyId) {
//            company = companyService.findByUserId(user.getId());
//        }else{
//            return user;
//        }
//        if (null == company) {
//            throw new DefaultException(401, "exception.user.isnull");
//        }
//        user.setCompanyId(company.getId());
//        return user;
//    }

    /**
     * 获取token数据
     * @return
     */
    protected String getHeadersInfo() {
        HttpServletRequest req = this.getRequest();
        String token = req.getHeader("Authorization");
        return token;
    }

    /**
     * 获取国家化数据
     * @param key
     * @return
     */
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(key, null, locale);
        return msg;
    }

    /**
     * 统一异常处理返回接口
     * @param runtimeException
     * @return
     */
//    @ExceptionHandler(RuntimeException.class)
//    public Object runtimeExceptionHandler(RuntimeException runtimeException)  {
//        HttpServletResponse httpServletResponse = getResponse();
//        Map model = new HashMap();
//        if ( runtimeException instanceof DefaultException) {
//            DefaultException defaultException = (DefaultException)runtimeException;
//
//            if (!defaultException.isLocalMessage()) {
//                model.put("message", defaultException.getMessageKey());
//            } else if (null == defaultException.getMessageParams()) {
//                model.put("message", getMessage(defaultException.getMessageKey()));
//            } else {
//                String strMessage = getMessage(defaultException.getMessageKey());
//                List<String> params = defaultException.getMessageParams();
//                for (int i = 0; i < params.size(); i++) {
//                    strMessage = strMessage.replace("{" + i + "}", params.get(i));
//                }
//                model.put("message", strMessage);
//            }
//
//            httpServletResponse.setStatus(defaultException.getHttpStatusCode());
//        } else {
//            model.put("message", getMessage("exception.default.500"));
//            httpServletResponse.setStatus(500);
//        }
//
//        LOGGER.error(getExceptionMessage(runtimeException));
//
//        return model;
//    }

    /**
     * 获取异常信息的内容
     * @param e
     * @return
     */
    public String getExceptionMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validationBodyException(MethodArgumentNotValidException exception){
        System.out.println("this is MethodArgumentNotValidException");
        BindingResult result = exception.getBindingResult();
        List listFieldErrors = new ArrayList();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                LOGGER.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                Map<String, String> map = new HashMap<>();
                map.put("field", fieldError.getField());
                map.put("msg", fieldError.getDefaultMessage());
                listFieldErrors.add(map);
            });
        }

        Map model = new HashMap();
        model.put("fieldErrors", listFieldErrors);
        HttpServletResponse httpServletResponse = getResponse();
        httpServletResponse.setStatus(500);
        return model;
    }

    public Locale getLocal() {
        return LocaleContextHolder.getLocale();
    }

    /**
     * 获取语言
     * @return
     */
    public String getLanguage() {
        Locale locale=getLocal();
        if(null==locale){
            return null;
        }
        String language = getLocal().getLanguage();
        language = "<" + language + ">";
        return language;
    }

//    public HttpHeaders getDownloadFileHeaders (String fileName) {
//        HttpHeaders headers = new HttpHeaders();
//        String strFileName = StringUtils.encode(fileName);
//        headers.setContentDispositionFormData("attachment", strFileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return headers;
//    }

}
