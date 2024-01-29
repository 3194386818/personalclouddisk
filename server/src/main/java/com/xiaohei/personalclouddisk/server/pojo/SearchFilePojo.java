package com.xiaohei.personalclouddisk.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchFilePojo {
    // 搜索的关键字
    @NotNull(message = "key不能为null")
    private String key;
    // 搜索的目录
    private String dir = "/";
    // 开始的页数
    private Integer page = 1;
    // 返回搜索的数量
    private Integer num = 500;
    // 是否递归，1递归, 0不递归
    private Integer recursion = 1;
}
