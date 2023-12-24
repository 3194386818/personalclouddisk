package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.pojo.Quota;
import com.xiaohei.personalclouddisk.server.service.QuotaService;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class QuotaServiceImpl implements QuotaService {

    @Autowired
    private Config config;

    @Override
    public Quota getData() {
        String diskPath = config.queryValue(Config.DISK_PATH);
        if (StringUtils.isEmpty(diskPath)) {
            return null;
        }
        File file = new File(diskPath);
        return new Quota(file.getTotalSpace(), file.getUsableSpace());
    }
}
