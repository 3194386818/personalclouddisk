package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.service.GetFileInformationService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetFileInformationServiceImpl implements GetFileInformationService {

    private FileQueryDao fileQueryDao;

    @Override
    public List<FilePojo> getFileList(FileRequest fileRequest) {
        return null;
    }
}
