package com.xiaohei.personalclouddisk.server.service;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;

/**
 * 文件下载的接口
 */
public interface DownloadFileService {


    /**
     * 通过文件id获取文件的byte[]，这个只能获取文件，一般网页下载就使用这个
     * @param fid 文件的id
     * @return
     */
    FilePojo simpleDownload(String fid);

}
