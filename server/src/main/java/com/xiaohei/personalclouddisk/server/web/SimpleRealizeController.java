package com.xiaohei.personalclouddisk.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/simple/api")
public class SimpleRealizeController {


    @GetMapping("/list")
    public String listFile(@RequestParam(name = "dir", defaultValue = "/") String dir) {
        return "";
    }
}
