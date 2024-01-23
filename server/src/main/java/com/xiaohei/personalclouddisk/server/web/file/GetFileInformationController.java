package com.xiaohei.personalclouddisk.server.web.file;

import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.ResultData;
import com.xiaohei.personalclouddisk.server.service.GetFileInformationService;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class GetFileInformationController {

    private Config config;
    private GetFileInformationService getFileInformationService;

    @GetMapping("/list")
    public ResultData getFileList(FileRequest fileRequest) {
        if (!FileUtils.isClientPathExists(config.queryValue(Config.DISK_PATH), fileRequest.getDir())) {
            return ResultData.directoryNot(null);
        }

        List<FilePojo> fileList = getFileInformationService.getFileList(fileRequest);
        if (fileList == null) {
            fileList = new ArrayList<>();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("thisPath", FileUtils.serverPathToClientPath(config.queryValue(Config.DISK_PATH), Paths.get(fileRequest.getDir())));
        map.put("data", fileList);


        return ResultData.success(map);
    }


}
