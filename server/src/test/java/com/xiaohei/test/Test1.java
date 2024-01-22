package com.xiaohei.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Test1 {

    @Test
    public void test1() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 1);

        System.out.println(map.get("a"));
    }

    @Test
    public void pathsTest() throws IOException {

        String p = "C:\\Users\\dell\\Downloads\\apowermirrorpro-setup-saaspro.exe";
        String a = "C:\\Users\\dell\\Downloads";
        String b = "/aa/abs.png";
        System.out.println(Paths.get(a, b).toAbsolutePath().toString());

    }


}
