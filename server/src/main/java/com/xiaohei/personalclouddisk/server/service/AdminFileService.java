package com.xiaohei.personalclouddisk.server.service;


import java.io.IOException;

public interface AdminFileService {
    /**
     * 复制文件操作
     * @param path 对应的文件绝对路径
     * @param dir 要复制到哪里的目录
     * @return 返回是否复制成功
     */
    boolean copy(String path, String dir);

    /**
     * 移动文件操作
     * @param path 文件的绝对路径
     * @param newPath 要存放文件的绝对路径
     * @return 是否移动成功
     */
    boolean move(String path, String newPath) throws IOException;

    /**
     * 文件重命名操作
     * @param path 文件的绝对路径
     * @param newName 新名字
     * @return 是否操作成功
     */
    boolean rename(String path, String newName) throws IOException;

    /**
     * 文件删除操作
     * @param path 文件的绝对路径
     * @return 是否操作成功
     */
    boolean delete(String path) throws IOException;
}
