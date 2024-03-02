package com.xiaohei.personalclouddisk.server.utils;

import com.xiaohei.personalclouddisk.server.dao.Config;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 这个类，用来代替Config的获取值
 */
@Component
@AllArgsConstructor
public class GetConfigValue {
    private Config config;

    public String getDisk_path() {
        return config.queryValue(Config.DISK_PATH);
    }
}
