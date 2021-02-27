package com.wellhope.springbootall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author GaoJ
 * @create 2021-02-26 23:16
 */
@Component
@ConfigurationProperties(prefix = "resource")//如果属性和配置文件一样可用此方法，否则在属性上加@Value("${resource.imageServer}")
public class Resource {
   // @Value("${resource.imageServer}")
    private String imageServer;
 //   @Value("${resource.emailServer}")
    private String emailServer;

    public String getImageServer() {
        return imageServer;
    }

    public void setImageServer(String imageServer) {
        this.imageServer = imageServer;
    }

    public String getEmailServer() {
        return emailServer;
    }

    public void setEmailServer(String emailServer) {
        this.emailServer = emailServer;
    }
}
