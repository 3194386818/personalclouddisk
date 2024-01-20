package com.xiaohei.personalclouddisk.server.web.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/file")
public class GetFileInformation {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class FileRequest {
        // 需要查看文件的目录，默认为:/
        private String dir = "/";
        /*
        排序方法:
            name: 以名字排序，默认值
            time: 以时间排序
            size: 以大小排序
         */
        @Pattern(regexp = "^(name|time|size)$", message = "Invalid value. Must be 'name', 'size', or 'time'.")
        private String order = "name";
        /*
        升序: 0
        降序: 1
         */
        private Integer desc = 0;
        // 开始文件的索引
        private Integer start = 0;
        // 查询数目
        private Integer limit = 1000;
        /*
        是否只返回文件夹，0返回所有，1只返回文件夹
         */
        private Integer folder = 0;
    }

    @GetMapping("/list")
    public String getFileList(@RequestParam FileRequest fileRequest) {
        return "";
    }


}
