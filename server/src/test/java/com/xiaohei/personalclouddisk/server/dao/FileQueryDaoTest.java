package com.xiaohei.personalclouddisk.server.dao;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.pojo.FileRequest;
import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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
        fileRequest.setOrder(FileRequest.ORDER_TIME);
        fileRequest.setDesc(1);
        fileRequest.setLimit(20);

        List<FilePojo> filePojos = fileQueryDao.queryFile(fileRequest);
        System.out.println("\n\n");
        filePojos.forEach(System.out::println);
        System.out.println("\n\n");
    }


    private static SearchFilePojo searchFilePojo = null;
    static {
        if (searchFilePojo == null) {
            searchFilePojo = new SearchFilePojo();
            searchFilePojo.setDir("D:\\520\\临时");
            searchFilePojo.setKey("class");
            searchFilePojo.setNum(500);
            searchFilePojo.setPage(23);
            searchFilePojo.setRecursion(1);
        }
    }

    @Test
    public void searchFileData() {
        List<FilePojo> filePojos = fileQueryDao.searchFileData(searchFilePojo);
        Long size = fileQueryDao.searchFileCount(searchFilePojo);
        Long AllPage = (size / searchFilePojo.getNum()) + (size % searchFilePojo.getNum() == 0 ? 0 : 1);

        System.out.println();
    }

    @Test
    public void searchFileCount() {
        Long aLong = fileQueryDao.searchFileCount(null);
        System.out.println(aLong);
    }
}
