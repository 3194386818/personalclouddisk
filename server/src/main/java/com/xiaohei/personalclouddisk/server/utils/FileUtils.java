package com.xiaohei.personalclouddisk.server.utils;

import com.xiaohei.personalclouddisk.server.pojo.FilePojo;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

// 关于文件相关的工具类
public class FileUtils {

    private FileUtils(){}

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
     *
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
     *
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

    /**
     * 将服务器的真实路径隐藏，如果服务器的是以`\`问分隔符，就统一转化为`/`
     *
     * @param prefix 要隐藏的前缀
     * @param path   服务器的真实路径
     * @return 客户端看到的路径
     */
    public static String serverPathToClientPath(String prefix, Path path) {
        if (StringUtils.isEmpty(prefix) || path == null || !Files.exists(path)) {
            return null;
        }

        String absolutePath = path.toAbsolutePath().toString();
        if (absolutePath.contains(prefix)) {
            // 去除前缀
            String vmPath = absolutePath.substring(prefix.length());
            // 替换转义符
            if (vmPath.contains("\\")) {
                vmPath = vmPath.replace("\\", "/");
            }
            return vmPath;
        }
        return null;
    }

    /**
     * 将客户端的相对路径转换成服务器的绝对路径，将文件分隔符转为服务器相对应的分隔符
     * @param prefix 前缀路径
     * @param path 客户端路径
     * @return 绝对路径
     */
    public static String clientPathToServerPath(String prefix, String path) {
        if (StringUtils.isEmpty(prefix) || StringUtils.isEmpty(path)) {
            return null;
        }

        return Paths.get(prefix, path).toAbsolutePath().toString();
    }

    /**
     * 判断路径是否存在
     * @param prefix
     * @param path
     * @return
     */
    public static boolean isClientPathExists(String prefix, String path) {
        String serverPath = clientPathToServerPath(prefix, path);
        if (serverPath == null) {
            return false;
        }
        return Files.exists(Paths.get(serverPath));
    }

    /**
     * 截取子路径
     * @param path 绝对路径
     * @param par 想要去除的父路径
     * @return
     */
    public static String getSubPath(Path path, Path par) {
        if (path == null && par == null) return null;

        String pathS = path.toAbsolutePath().toString();
        String parS = par.toAbsolutePath().toString();

        int index = parS.length();
        return pathS.substring(index);
    }


    /**
     * 对转换路径的封装, 在应用程序启动时就加载到Bean里面了
     */
    @AllArgsConstructor
    @Setter
    public static class PathConversion {
        private String prefix;

        public String serverPathToClientPath(Path path) {
            return FileUtils.serverPathToClientPath(prefix, path);
        }

        public String clientPathToServerPath(String path) {
            return FileUtils.clientPathToServerPath(prefix, path);
        }
    }
}
