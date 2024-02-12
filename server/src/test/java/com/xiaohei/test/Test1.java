package com.xiaohei.test;

import com.xiaohei.personalclouddisk.server.utils.HashMapUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Test1 {

    @Test
    public void test1() throws IOException {
        for (int i = 0; i < 5; i++) {
            System.out.println(System.nanoTime());
            System.out.println(System.currentTimeMillis());
        }

    }


}
