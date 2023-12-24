package com.xiaohei.personalclouddisk.server.web;

import com.xiaohei.personalclouddisk.server.pojo.Quota;
import com.xiaohei.personalclouddisk.server.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotaController {

    @Autowired
    private QuotaService quotaService;


    @GetMapping("/quota")
    public Quota quota() {
        return quotaService.getData();
    }

}
