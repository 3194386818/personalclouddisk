package com.xiaohei.personalclouddisk.server.config;

import com.xiaohei.personalclouddisk.server.PersonalCloudDiskProperties;
import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    private final Config config;

    public MyConfiguration(Config config) {
        this.config = config;
    }

    @Bean(name = "pathConversion")
    public FileUtils.PathConversion initPathConversion() {
        return new FileUtils.PathConversion(config.queryValue(Config.DISK_PATH));
    }
}
