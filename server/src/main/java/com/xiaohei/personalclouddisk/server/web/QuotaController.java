/*
* 容量数据
* */
package com.xiaohei.personalclouddisk.server.web;

import com.xiaohei.personalclouddisk.server.pojo.Quota;
import com.xiaohei.personalclouddisk.server.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotaController {
    private QuotaService quotaService;

    public QuotaController(QuotaService quotaService) {
        this.quotaService = quotaService;
    }

    @GetMapping("/quota")
    public Quota quota() {
        return quotaService.getData();
    }

}
