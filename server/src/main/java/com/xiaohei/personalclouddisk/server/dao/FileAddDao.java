package com.xiaohei.personalclouddisk.server.dao;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileAddDao {

    boolean simpleAddFile(FilePojo filePojo);
}
