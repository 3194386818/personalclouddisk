package com.xiaohei.personalclouddisk.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "personalclouddisk")
public class PersonalCloudDiskProperties {

    // 是否启动的时候检查文件是否和数据库记录的一致
    private boolean initCheck;
}
