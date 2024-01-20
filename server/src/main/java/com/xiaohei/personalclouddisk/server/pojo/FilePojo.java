package com.xiaohei.personalclouddisk.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilePojo {
    // 文件在云端的唯一标识ID
    private Long fs_id;
    // 文件的绝对路径
    private String path;
    // 文件名称
    private String server_filename;
    // 文件大小，单位B
    private Long size;
    // 文件在服务器修改时间
    private Long server_mtime;
    // 文件在服务器创建时间
    private Long server_ctime;
    // 文件在客户端修改时间
    private Long local_mtime;
    // 文件在客户端创建时间
    private Long local_ctime;
    // 是否为目录，0 文件、1 目录
    private Byte isdir;
    // 文件类型，1 视频、2 音频、3 图片、4 其他
    private Integer category;
    // MD5，只有是文件类型时，该字段才存在
    private String md5;
    // 该目录是否存在子目录， 0为存在， 1为不存在
    private Integer dir_empty;
}
