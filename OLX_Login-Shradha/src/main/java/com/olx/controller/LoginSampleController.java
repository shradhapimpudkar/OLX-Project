package com.olx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class LoginSampleController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @GetMapping("/read-config")
    public String getDbConfig(){
        return "OLXLOGIN-DB_URL -> " + dbUrl;
    }
}
