package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.service.DownloadFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DownloadServiceImpl implements DownloadFileService {

    private FileQueryDao fileQueryDao;


    @Override
    public FilePojo simpleDownload(String fid) {
        if (fid == null) return null;
        return fileQueryDao.queryFileByFSID(fid);
    }
}
