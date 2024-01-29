package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;
import com.xiaohei.personalclouddisk.server.service.GetFileInformationService;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetFileInformationServiceImpl implements GetFileInformationService {

    private FileQueryDao fileQueryDao;
    private Config config;

    @Override
    public List<FilePojo> getFileList(FileRequest fileRequest) {
        fileRequest.setDir(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), fileRequest.getDir()));

        List<FilePojo> filePojos = fileQueryDao.queryFile(fileRequest);
        if (filePojos == null || filePojos.size() == 0) {
            return filePojos;
        }
        return filePojos.stream().map((filePojo) -> {
            filePojo.setPath(FileUtils.serverPathToClientPath(config.queryValue(Config.DISK_PATH), Paths.get(filePojo.getPath())));
            return filePojo;
        }).collect(Collectors.toList());
    }

    @Override
    public FilePojo queryFileInfo(String fs_id) {
        FilePojo filePojo = fileQueryDao.queryFileByFSID(fs_id);
        filePojo.setPath(FileUtils.serverPathToClientPath(config.queryValue(Config.DISK_PATH), Paths.get(filePojo.getPath())));
        return filePojo;
    }

    @Override
    public List<FilePojo> searchFile(SearchFilePojo searchFile, boolean b) {
        return null;
    }
}
