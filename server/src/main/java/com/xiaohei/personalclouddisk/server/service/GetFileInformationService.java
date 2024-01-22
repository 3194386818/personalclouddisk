package com.xiaohei.personalclouddisk.server.service;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;

import java.util.List;

public interface GetFileInformationService {

    List<FilePojo> getFileList(FileRequest fileRequest);
}
