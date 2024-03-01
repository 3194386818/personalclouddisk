package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.service.DownloadFileService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;


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
