package com.powerwolf.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   //动态刷新(需要发送post请求：http://loalhost:3355/actuator/refresh)
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config")
    public String getConfigInfo(){
        return configInfo;
    }
}
