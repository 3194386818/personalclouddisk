package com.xiaohei.personalclouddisk.server.dao;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过文件id查询
     * @param fs_id
     * @return
     */
    FilePojo queryFileByFSID(String fs_id);

    /**
     * 通过 searchFilePOJO 查询指定的数据
     * @param searchFilePojo
     * @return
     */
    List<FilePojo> searchFileData(SearchFilePojo searchFilePojo);

    /**
     * 查询他的size
     * @param searchFilePojo
     * @return
     */
    Long searchFileCount(SearchFilePojo searchFilePojo);
}
