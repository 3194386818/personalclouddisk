package com.xiaohei.personalclouddisk.server;

import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.dao.FileAddDao;
import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class InitCheck implements ApplicationRunner {

    private FileAddDao fileAddDao;
    private FileQueryDao fileQueryDao;
    private Config config;
    private PersonalCloudDiskProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (properties.isInitCheck()) {
            String diskPath = config.queryValue(Config.DISK_PATH);
            Path path = Paths.get(diskPath);
            List<Path> allFileAndDir = FileUtils.getAllFileAndDir(path);

            for (Path p : allFileAndDir) {
                String absolutePath = p.getParent() + File.separator + p.getFileName();
                // 在服务器存在就跳过
                if(fileQueryDao.queryFileByPath(absolutePath) != null) {
                    continue;
                }
                // 添加步骤
                FilePojo filePojo = FileUtils.addPathToServer(p);
                log.info("添加了: " + filePojo.getPath());
                fileAddDao.simpleAddFile(filePojo);
            }
        }
    }


}
