package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;
import com.xiaohei.personalclouddisk.server.service.GetFileInformationService;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import com.xiaohei.personalclouddisk.server.utils.GetConfigValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetFileInformationServiceImpl implements GetFileInformationService {

    private FileQueryDao fileQueryDao;
    private GetConfigValue getConfigValue;


    @Override
    public List<FilePojo> getFileList(FileRequest fileRequest) {
        fileRequest.setDir(FileUtils.clientPathToServerPath(getConfigValue.getDisk_path(), fileRequest.getDir()));

        List<FilePojo> filePojos = fileQueryDao.queryFile(fileRequest);
        if (filePojos == null || filePojos.size() == 0) {
            return filePojos;
        }
        return filePojos.stream().map((filePojo) -> {
            filePojo.setPath(FileUtils.serverPathToClientPath(getConfigValue.getDisk_path(), Paths.get(filePojo.getPath())));
            return filePojo;
        }).collect(Collectors.toList());
    }

    @Override
    public FilePojo queryFileInfo(String fs_id) {
        FilePojo filePojo = fileQueryDao.queryFileByFSID(fs_id);
        filePojo.setPath(FileUtils.serverPathToClientPath(getConfigValue.getDisk_path(), Paths.get(filePojo.getPath())));
        return filePojo;
    }

    @Override
    public List<FilePojo> searchFile(SearchFilePojo searchFile, boolean[] b) {
        // 获取有多少条匹配的元素
        Long itemCount = fileQueryDao.searchFileCount(searchFile);
        // 计算出全部页面
        long allPageNum = (itemCount / searchFile.getNum()) + (itemCount % searchFile.getNum() == 0 ? 0 : 1);
        // 是否有下一页
        b[0] = allPageNum > searchFile.getPage();
        // data
        List<FilePojo> filePojos = fileQueryDao.searchFileData(searchFile);
        filePojos.forEach(v -> v.setPath(FileUtils.serverPathToClientPath(getConfigValue.getDisk_path(), Paths.get(v.getPath()))));
        return filePojos;
    }
}

