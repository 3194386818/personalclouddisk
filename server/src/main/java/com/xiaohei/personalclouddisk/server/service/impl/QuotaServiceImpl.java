package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.pojo.Quota;
import com.xiaohei.personalclouddisk.server.service.QuotaService;
import com.xiaohei.personalclouddisk.server.utils.GetConfigValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class QuotaServiceImpl implements QuotaService {

    @Autowired
    private GetConfigValue getConfigValue;

    @Override
    public Quota getData() {
        String diskPath = getConfigValue.getDisk_path();
        if (StringUtils.isEmpty(diskPath)) {
            return null;
        }
        File file = new File(diskPath);
        return new Quota(file.getTotalSpace(), file.getUsableSpace());
    }
}
