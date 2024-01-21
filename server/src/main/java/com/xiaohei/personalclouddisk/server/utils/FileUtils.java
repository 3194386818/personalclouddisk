package com.xiaohei.personalclouddisk.server.utils;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.http.MediaType;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

// 关于文件相关的工具类
public class FileUtils {

    /**
     * 获取指定目录下的全部(包括子目录)文件和目录
     *
     * @param path
     * @return
     */
    public static List<Path> getAllFileAndDir(Path path) {
        if (path == null || !Files.exists(path) || !Files.isDirectory(path)) {
            return null;
        }

        List<Path> allFileAndDir = new ArrayList<>();
        Queue<Path> tmpDir = new LinkedList<>();

        allFileAndDir.add(path);
        tmpDir.add(path);
        while (tmpDir.size() > 0) {
            DirectoryStream<Path> paths = null;
            try {
                paths = Files.newDirectoryStream(tmpDir.poll());
                for (Path p : paths) {
                    if (Files.isDirectory(p)) {
                        allFileAndDir.add(p);
                        tmpDir.add(p);
                    } else {
                        allFileAndDir.add(p);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    paths.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allFileAndDir;
    }

    /**
     * 将path的数据添加到FilePojo为添加到数据库做准备
     *
     * @param path
     * @return
     */
    public static FilePojo addPathToServer(Path path) throws IOException {
        if (path == null || !Files.exists(path)) {
            return null;
        }

        // 添加数据步骤
        FilePojo filePojo = new FilePojo();
        filePojo.setPath(path.getParent() + File.separator + path.getFileName());
        filePojo.setServer_filename(path.getFileName().toString());
        long currentTime = System.currentTimeMillis();
        filePojo.setServer_ctime(currentTime);
        filePojo.setServer_mtime(currentTime);
        filePojo.setLocal_ctime(Files.readAttributes(path, BasicFileAttributes.class).creationTime().toMillis());
        filePojo.setLocal_mtime(Files.getLastModifiedTime(path).toMillis());


        if (Files.isDirectory(path)) {
            // 目录
            filePojo.setIsdir((byte) 1);
            filePojo.setDir_empty(isDirEmpty(path) ? 0 : 1);
        } else {
            // 文件
            filePojo.setIsdir((byte) 0);
            filePojo.setSize(Files.size(path));
            filePojo.setCategory(getCategory(path));
            filePojo.setMd5(DigestUtils.md5Hex(new FileInputStream(path.toFile())).toUpperCase());
        }


        return filePojo;
    }

    /**
     * 通过Path解析为指定的类别编号
     * @param path 文件类型的
     * @return 指定编号
     * @throws IOException
     */
    public static Integer getCategory(Path path) throws IOException {
        if (path == null || !Files.exists(path) || Files.isDirectory(path)) {
            return null;
        }
        /**
         * video: 视频,1
         * audio: 音频,2
         * image: 图片,3
         * null: 其他,4
         */
        Map<String, Integer> map = new HashMap<>();
        map.put("video", 1);
        map.put("audio", 2);
        map.put("image", 3);

        // 解析类别对应的编号
        String s = Files.probeContentType(path);
        if (s != null) {
            MediaType mediaType = MediaType.parseMediaType(s);
            Integer id = map.get(mediaType.getType());
            return id == null ? 4 : id;
        } else {
            return 4;
        }
    }

    /**
     * 是否存在子目录
     * @param path 必须为目录类型
     * @return
     */
    public static Boolean isDirEmpty(Path path) {
        if (path == null || !Files.exists(path) || !Files.isDirectory(path)) {
            return null;
        }

        DirectoryStream<Path> paths = null;
        try {
            paths = Files.newDirectoryStream(path);
            for (Path p : paths) {
                if (Files.isDirectory(p)) {
                    return Boolean.TRUE;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                paths.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Boolean.FALSE;
    }

}
