package com.xiaohei.personalclouddisk.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 简单的文件封装类
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleFileBase {
    private String name;
    private String path;
    private Date createDate;
}
