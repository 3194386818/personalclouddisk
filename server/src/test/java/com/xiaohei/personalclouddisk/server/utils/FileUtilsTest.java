package com.xiaohei.personalclouddisk.server.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void getSubPath() {
        System.out.println(FileUtils.getSubPath(Paths.get("D:\\520\\临时\\NodejsWebApp1\\.vs\\NodejsWebApp1"), Paths.get("D:\\520\\临时")));
    }
}
