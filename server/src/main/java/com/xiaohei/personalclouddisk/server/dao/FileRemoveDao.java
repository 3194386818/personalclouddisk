package com.xiaohei.personalclouddisk.server.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRemoveDao {
    boolean deleteFileByPath(String path);
}
