package com.xiaohei.personalclouddisk.server.web.file;

import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class GetFileInformation {


    @GetMapping("/list")
    public String getFileList(@RequestParam FileRequest fileRequest) {
        return "";
    }


}
