package com.xiaohei.personalclouddisk.server.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PrecreatePojo {
    private String path;
    private Integer size;
    @JsonProperty("block_list")
    private List<String> blockList;
    private Integer isdir;
    private String rtype;
    private String uploadid;
    @JsonProperty("content_md5")
    private String contentMd5;
    @JsonProperty("slice_md5")
    private String sliceMd5;
}
