package com.xiaohei.personalclouddisk.server.service.impl;

import com.xiaohei.personalclouddisk.server.dao.FileAddDao;
import com.xiaohei.personalclouddisk.server.dao.FileQueryDao;
import com.xiaohei.personalclouddisk.server.dao.FileRemoveDao;
import com.xiaohei.personalclouddisk.server.dao.FileUpdateDao;
import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import com.xiaohei.personalclouddisk.server.service.AdminFileService;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminFileServiceImpl implements AdminFileService {

    private FileAddDao fileAddDao;
    private FileRemoveDao fileRemoveDao;
    private FileUpdateDao fileUpdateDao;
    private FileQueryDao fileQueryDao;

    @Override
    public boolean copy(String path, String dir) {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(dir)) return false;

        try {
            Path path1 = Paths.get(path);
            Path path2 = Paths.get(dir, path1.getFileName().toString());
            // 判断是否为目录，如果是目录那也需要复制子文件到数据库
            if (Files.isDirectory(path1)) {
                org.apache.commons.io.FileUtils.copyDirectory(path1.toFile(), path2.toFile());
                List<Path> allFileAndDir = FileUtils.getAllFileAndDir(path1);
                for (Path path3 : allFileAndDir) {
                    path3 = Paths.get(path2.toAbsolutePath().toString(), FileUtils.getSubPath(path3, path1));
                    fileAddDao.simpleAddFile(FileUtils.addPathToServer(path3));
                }
            } else {
                org.apache.commons.io.FileUtils.copyFile(path1.toFile(), path2.toFile());
                fileAddDao.simpleAddFile(FileUtils.addPathToServer(path2));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean move(String path, String newPath) throws IOException {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(newPath)) return false;

        Path path1 = Paths.get(path);
        Path path2 = Paths.get(newPath);


        if (Files.isDirectory(path1)) {
            List<Path> allFileAndDir = FileUtils.getAllFileAndDir(path1);
            for (Path path3 : allFileAndDir) {
                // 目录操作方法
                Path path4 = Paths.get(newPath, FileUtils.getSubPath(path3, path1));
                doMove(path3.toAbsolutePath().toString(), path4.toAbsolutePath().toString());
            }
        } else {
            // 移动文件处理方法
            return doMove(path, newPath);
        }
        Files.move(path1, path2);
        return true;
    }

    @Override
    public boolean rename(String path, String newName) throws IOException {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(newName)) return false;
        return move(path, Paths.get(Paths.get(path).getParent().toString(), newName).toAbsolutePath().toString());
    }

    @Override
    public boolean delete(String path) throws IOException {
        if (path == null) return false;
        Path path1 = Paths.get(path);
        if (Files.isDirectory(path1)) {
            return deleteDir(path1);
        } else {
            return deleteFile(path1);
        }
    }

    /**
     * 删除文件
     * @param path 文件的绝对路径
     * @return
     * @throws IOException
     */
    private boolean deleteFile(Path path) throws IOException {
        if (path == null) return false;

        Files.delete(path);
        fileRemoveDao.deleteFileByPath(path.toAbsolutePath().toString());
        return true;
    }

    /**
     * 删除目录
     * @param path 目录的绝对路径
     * @return
     * @throws IOException
     */
    private boolean deleteDir(Path path) throws IOException {
        if (path == null || !Files.isDirectory(path)) return false;

        List<Path> collect = FileUtils.getAllFileAndDir(path).stream()
                .sorted(Comparator.comparingInt(o -> Files.isDirectory(o) ? 1 : 0))
                .collect(Collectors.toList());
        for (Path path1 : collect) {
            deleteFile(path1);
        }

        return true;
    }

    /**
     * 执行一次移动操作，仅操作数据库
     * @param path 要操作的文件路径
     * @param newPath 行的文件绝对路径
     * @return
     */
    private boolean doMove(String path, String newPath) {
        FilePojo filePojo = fileQueryDao.queryFileByPath(path);
        filePojo.setPath(newPath);
        filePojo.setServer_filename(Paths.get(newPath).getFileName().toString());
        return fileUpdateDao.updateFileDataByPath(path, filePojo);
    }

}
