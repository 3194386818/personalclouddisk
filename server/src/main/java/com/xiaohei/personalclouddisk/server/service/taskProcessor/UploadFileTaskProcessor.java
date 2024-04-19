package com.xiaohei.personalclouddisk.server.service.taskProcessor;

import com.xiaohei.personalclouddisk.server.pojo.PrecreatePojo;
import org.springframework.stereotype.Component;

@Component
public class UploadFileTaskProcessor {
    /**
     * 上传任务的唯一id
     */
    private String uploadID;

    private PrecreatePojo precreatePojo;

    /**
     * 创建一个文件上传的任务
     * @param precreatePojo
     * @return
     */
    public boolean createTask(PrecreatePojo precreatePojo) {
        if (precreatePojo == null) return false;


    }


    public String getUploadID() {
        return uploadID;
    }
}
