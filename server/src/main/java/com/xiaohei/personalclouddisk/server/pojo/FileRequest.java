package com.xiaohei.personalclouddisk.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class FileRequest {
    public static final String ORDER_NAME = "name";
    public static final String ORDER_TIME = "time";
    public static final String ORDER_SIZE = "size";

    /**
     * 需要查看文件的目录，默认为:/
     */
    private String dir = "/";
    /**
     * 排序方法:
     * name: 以名字排序，默认值
     * time: 以时间排序
     * size: 以大小排序
     */
    @Pattern(regexp = "^(name|time|size)$", message = "Invalid value. Must be 'name', 'size', or 'time'.")
    private String order = "name";
    /**
     * 升序: 0
     * 降序: 1
     */
    private Integer desc = 0;
    // 开始文件的索引
    private Integer start = 0;
    // 查询数目
    private Integer limit = 1000;
    /**
     * 是否只返回文件夹，0返回所有，1只返回文件夹
     */
    private Integer folder = 0;
}
