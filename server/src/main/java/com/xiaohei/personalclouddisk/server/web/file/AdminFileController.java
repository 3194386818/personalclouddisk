package com.xiaohei.personalclouddisk.server.web.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaohei.personalclouddisk.server.dao.Config;
import com.xiaohei.personalclouddisk.server.pojo.ResultData;
import com.xiaohei.personalclouddisk.server.service.AdminFileService;
import com.xiaohei.personalclouddisk.server.utils.FileUtils;
import com.xiaohei.personalclouddisk.server.utils.HashMapUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class AdminFileController {

    private AdminFileService adminFileService;
    private Config config;

    public AdminFileController(AdminFileService adminFileService, Config config) {
        this.adminFileService = adminFileService;
        this.config = config;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    // 定义一个临时的类
    class T_Json_Item {
        private String path;
        private String dest;
        private String newname;
    }

    @PostMapping("/file/filemanager")
    public ResultData manager(@RequestParam("opera") String opera,@RequestBody String json) {
        if (StringUtils.isEmpty(opera) || StringUtils.isEmpty(json)) {
            return ResultData.parameterError(HashMapUtils.empty());
        }


        Gson gson = new Gson();
        List<T_Json_Item> list = gson.fromJson(json, new TypeToken<ArrayList<T_Json_Item>>() {
        }.getType());



        // 选择处理方式
        switch (opera) {
            case "copy": return copy(list);
            case "move": return move(list);
            case "rename": return rename(list);
            case "delete": return delete(list);
            default: return ResultData.parameterError(new HashMapUtils<String, Object>().put("msg", "opera参数只能为: copy、move、rename、delete").builder());
        }
    }

    // 复制操作
    private ResultData copy(List<T_Json_Item> t_json_itemList) {
        for(T_Json_Item t_json_item : t_json_itemList) {
            if (isPath(t_json_item) && !StringUtils.isEmpty(t_json_item.getDest())) {
                t_json_item.setPath(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), t_json_item.getPath()));
                t_json_item.setDest(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), t_json_item.getDest()));
                adminFileService.copy(t_json_item.path, t_json_item.getDest());
            } else {
                return ResultData.parameterError(new HashMapUtils<String, Object>().put("msg", "缺少`path`或`dest`属性:\n" + t_json_item).builder());
            }
        }
        return ResultData.success(HashMapUtils.empty());
    }
    // 移动操作
    private ResultData move(List<T_Json_Item> t_json_itemList){
        for (T_Json_Item t_json_item : t_json_itemList) {
            if (isPath(t_json_item) && !StringUtils.isEmpty(t_json_item.getDest())) {
                t_json_item.setPath(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), t_json_item.getPath()));
                t_json_item.setDest(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), t_json_item.getDest()));
                try {
                    adminFileService.move(t_json_item.path, t_json_item.getDest());
                } catch (IOException e) {
                    return ResultData.fileNot(new HashMapUtils<String, Object>().put("msg", "服务器内部错误，移动操作失败").builder());
                }
            } else {
                return ResultData.parameterError(new HashMapUtils<String, Object>().put("msg", "缺少`path`或`dest`属性:\n" + t_json_item).builder());
            }
        }
        return ResultData.success(HashMapUtils.empty());
    }
    // 重命名操作
    private ResultData rename(List<T_Json_Item> t_json_itemList){
        for (T_Json_Item t_json_item : t_json_itemList) {
            if (isPath(t_json_item) && !StringUtils.isEmpty(t_json_item.getNewname())) {
                t_json_item.setPath(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), t_json_item.getPath()));
                try {
                    adminFileService.rename(t_json_item.path, t_json_item.getNewname());
                } catch (IOException e) {
                    return ResultData.fileNot(new HashMapUtils<String, Object>().put("msg", "服务器内部错误，重命名操作失败").builder());
                }
            } else {
                return ResultData.parameterError(new HashMapUtils<String, Object>().put("msg", "缺少`path`或`newname`属性:\n" + t_json_item).builder());
            }
        }
        return ResultData.success(HashMapUtils.empty());
    }
    // 删除操作
    private ResultData delete(List<T_Json_Item> t_json_itemList){
        for (T_Json_Item t_json_item : t_json_itemList) {
            if (isPath(t_json_item)) {
                t_json_item.setPath(FileUtils.clientPathToServerPath(config.queryValue(Config.DISK_PATH), t_json_item.getPath()));
                try {
                    adminFileService.delete(t_json_item.path);
                } catch (IOException e) {
                    return ResultData.fileNot(new HashMapUtils<String, Object>().put("msg", "服务器内部错误，删除操作失败").builder());
                }
            } else {
                return ResultData.parameterError(new HashMapUtils<String, Object>().put("msg", "缺少`path`属性:\n`" + t_json_item).builder());
            }
        }
        return ResultData.success(HashMapUtils.empty());
    }


    // 判断这个路径是否存在
    private boolean isPath(T_Json_Item t_json_item) {
        return !StringUtils.isEmpty(t_json_item.getPath());
    }
}
