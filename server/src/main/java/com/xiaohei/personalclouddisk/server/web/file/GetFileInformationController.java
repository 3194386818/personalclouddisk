package com.xiaohei.personalclouddisk.server.web.file;

import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.ResultData;
import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;
import com.xiaohei.personalclouddisk.server.service.GetFileInformationService;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import com.xiaohei.personalclouddisk.server.utils.HashMapUtils;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class GetFileInformationController {

    private Config config;
    private GetFileInformationService getFileInformationService;

    /**
     * 获取指定目录下的文件
     *
     * @param fileRequest
     * @return
     */
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

    /**
     * 通过文件id获取指定的文件信息
     *
     * @param fsId
     * @return
     */
    @GetMapping("/filemetas")
    public ResultData queryFileInfo(@RequestParam("fsid") String fsId) {
        FilePojo filePojo = getFileInformationService.queryFileInfo(fsId);
        if (filePojo == null || filePojo.getFs_id() == null) {
            return ResultData.parameterError(null);
        }

        return ResultData.success(Collections.singletonMap("data", filePojo));
    }

    /**
     * 搜索文件
     *
     * @param searchFilePojo
     * @return
     */
    @GetMapping("/search")
    public ResultData searchFile(@Validated SearchFilePojo searchFilePojo) {
        boolean[] has_more = new boolean[1];
        searchFilePojo.setDir(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), searchFilePojo.getDir()));

        List<FilePojo> filePojos = getFileInformationService.searchFile(searchFilePojo, has_more);
        return ResultData.success(new HashMapUtils<String, Object>().put("has_more", has_more[0] ? 1 : 0). put("data", filePojos).builder());
    }

    @ExceptionHandler(BindException.class)
    public ResultData yic(BindException bindException) {
        List<FieldError> fieldErrors = bindException.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            if (fieldError.getDefaultMessage().equals("key不能为null")) {
                return ResultData.parameterError(Collections.singletonMap("error", "key不能为null"));
            }
        }
        return ResultData.test(Collections.singletonMap("errors", "其他"));

    }
}
