package com.xiaohei.personalclouddisk.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quota {
    // 总空间大小，单位B
    private long total;
    // 已使用大小，单位B
    private long used;
}
