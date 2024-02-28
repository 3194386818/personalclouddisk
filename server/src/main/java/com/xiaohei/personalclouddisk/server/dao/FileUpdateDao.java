package com.xiaohei.personalclouddisk.server.dao;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileUpdateDao {

    /**
     * 通过绝对路径修改数据
     * @param filePojo
     * @return
     */
    boolean updateFileDataByPath(String p, FilePojo filePojo);
}
