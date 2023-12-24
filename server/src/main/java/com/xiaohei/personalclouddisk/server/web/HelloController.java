package com.xiaohei.personalclouddisk.server.web;

import com.xiaohei.personalclouddisk.server.dao.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    Config config;

    @GetMapping("/hello")
    public String hello() {
        return "<h1>hello</h1>";
    }

    @GetMapping("config/{key}")
    public String getConfig(@PathVariable String key) {
        return config.queryValue(key);
    }

}
