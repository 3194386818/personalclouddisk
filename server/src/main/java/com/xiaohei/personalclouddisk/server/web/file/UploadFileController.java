package com.xiaohei.personalclouddisk.server.web.file;

import com.xiaohei.personalclouddisk.server.pojo.PrecreatePojo;
import com.xiaohei.personalclouddisk.server.pojo.ResultData;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import com.xiaohei.personalclouddisk.server.utils.HashMapUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/file")
public class UploadFileController {

    private FileUtils.PathConversion pathConversion;

    public UploadFileController(@Qualifier("pathConversion") FileUtils.PathConversion pathConversion) {
        this.pathConversion = pathConversion;
    }

    @PostMapping("/precreate")
    public ResultData precreate(@RequestBody PrecreatePojo precreatePojo) {
        if (precreatePojo == null) return ResultData.parameterError(HashMapUtils.empty());

        precreatePojo.setPath(pathConversion.clientPathToServerPath(precreatePojo.getPath()));


        return ResultData.test(Collections.EMPTY_MAP);
    }
}
