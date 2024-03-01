package com.xiaohei.personalclouddisk.server.web.file;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.service.DownloadFileService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@AllArgsConstructor
public class DownloadFileController {

    private DownloadFileService downloadFileService;

    @GetMapping("/file/simpleDownload")
    public ResponseEntity<byte[]> simpleDownload(@RequestParam("fid") String fid){
        if (StringUtils.isEmpty(fid)) return null;

        FilePojo filePojo = downloadFileService.simpleDownload(fid);
        if(filePojo == null || StringUtils.isEmpty(filePojo.getPath()) || filePojo.getIsdir() == 1)return null;

        try(InputStream inputStream = new FileInputStream(filePojo.getPath())) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode(filePojo.getServer_filename(), "utf-8"));
            return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            return null;
        }
    }
}
