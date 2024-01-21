package com.xiaohei.personalclouddisk.server.dao;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileQueryDaoTest {

    @Autowired
    private FileQueryDao fileQueryDao;
    @Autowired
    private Config config;


    @Test
    public void queryFile() {
        FileRequest fileRequest = new FileRequest();
        Path path = Paths.get(config.queryValue(Config.DISK_PATH), "1.15.2");

        fileRequest.setDir(path.toAbsolutePath().toString());

        /**
         * TODO: 有个异常(Throwable)，回来的时候处理
         */
        List<FilePojo> filePojos = fileQueryDao.queryFile(fileRequest);
        System.out.println();
    }
}
