package com.xiaohei.personalclouddisk.server.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Config {
    String DISK_PATH = "disk_path";

    String queryValue(String key);
}
