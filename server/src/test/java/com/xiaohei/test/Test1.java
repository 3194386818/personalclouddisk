package com.xiaohei.test;

import com.xiaohei.personalclouddisk.server.utils.HashMapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Test1 {
    public static final Path path = Paths.get("D:\\nexus3\\sonatype-work\\nexus3\\cache\\bundle238\\version0.0\\n");

    @Test
    public void test1() throws IOException {
        FileUtils.copyDirectory(new File("D:\\520\\临时\\NodejsWebApp1\\.vs\\NodejsWebApp1"), new File("D:\\520\\临时\\NodejsWebApp1\\.vs\\bb\\NodejsWebApp1"));
    }


}
