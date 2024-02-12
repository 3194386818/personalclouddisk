package com.xiaohei.personalclouddisk.server.service;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;

import java.util.List;

public interface GetFileInformationService {

    List<FilePojo> getFileList(FileRequest fileRequest);

    FilePojo queryFileInfo(String fs_id);

    /**
     * 搜索文件
     * @param searchFile 搜索的pojo
     * @param b [0]用来返回是否还有下一页，true表示有下一页
     * @return 返回查询到的数据
     */
    List<FilePojo> searchFile(SearchFilePojo searchFile, boolean[] b);
}
