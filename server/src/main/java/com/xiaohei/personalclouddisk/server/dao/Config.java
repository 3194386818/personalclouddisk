package com.xiaohei.personalclouddisk.server.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Config {
    // 文件存放的路径
    String DISK_PATH = "disk_path";
    // 上传文件临时文件
    String TMP_PATH = "tmp_path";

    String queryValue(String key);
}
