package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.pojo.SearchFilePojo;
import com.xiaohei.personalclouddisk.server.service.GetFileInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GetFileInformationServiceImplTest {
    @Autowired
    private GetFileInformationService getFileInformationService;

    private static SearchFilePojo searchFilePojo = null;
    static {
        if (searchFilePojo == null) {
            searchFilePojo = new SearchFilePojo();
            searchFilePojo.setDir("D:\\520\\临时");
            searchFilePojo.setKey("class");
            searchFilePojo.setNum(500);
            searchFilePojo.setPage(5);
            searchFilePojo.setRecursion(1);
        }
    }
    @Test
    public void searchFile() {
        boolean[] b = new boolean[1];
        getFileInformationService.searchFile(searchFilePojo, b);
        System.out.println();
    }
}
