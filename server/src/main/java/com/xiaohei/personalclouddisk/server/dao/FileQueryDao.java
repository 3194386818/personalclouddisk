package com.xiaohei.personalclouddisk.server.dao;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileQueryDao {
    /**
     * 通过绝对路径查询
     * @param path
     * @return
     */
    FilePojo queryFileByPath(String path);

    /**
     * 主要的查询
     * @param fileRequest
     * @return
     */
    List<FilePojo> queryFile(FileRequest fileRequest);
}
