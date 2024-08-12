/*
* 容量数据
* */
package com.xiaohei.personalclouddisk.server.web;

import com.xiaohei.personalclouddisk.server.pojo.Quota;
import com.xiaohei.personalclouddisk.server.service.QuotaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotaController {
    private QuotaService quotaService;

    public QuotaController(QuotaService quotaService) {
        this.quotaService = quotaService;
    }

    @GetMapping("/quota")
    @CrossOrigin(origins = "*")
    public Quota quota() {
        return quotaService.getData();
    }

}
