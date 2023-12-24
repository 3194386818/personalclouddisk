package com.xiaohei.test;

import org.junit.Test;

import java.io.File;


public class Test1 {

    @Test
    public void test1() {
        File disk = new File("D:/");
        long freeSpace = disk.getFreeSpace();
        long totalSpace = disk.getTotalSpace();

        long freeSpaceGB  = freeSpace / (1024 * 1024 * 1024);
        long totalSpaceGB = totalSpace / (1024 * 1024 * 1024);

        System.out.println("磁盘总容量空间: " + totalSpaceGB + "GB, 磁盘剩余空间: " + freeSpaceGB + " GB");
    }
}
