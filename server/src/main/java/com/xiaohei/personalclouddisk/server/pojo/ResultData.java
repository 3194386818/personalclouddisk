package com.xiaohei.personalclouddisk.server.pojo;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Getter
public class ResultData<T> {
    private Integer code;
    private String msg;
    private Map<String, T> data;


    public ResultData(Integer code, String msg, Map<String, T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功请求就使用这个
     * @param data
     * @param <T>
     * @return
     */
    public static final <T> ResultData success(Map<String, T> data) {
        return new ResultData(0, "请求成功", data);
    }

    /**
     * 传入的参数错误
     * @param data
     * @param <T>
     * @return
     */
    public static final <T> ResultData parameterError(Map<String, T> data) {
        return new ResultData(2, "参数错误", data);
    }

    /**
     * 目录不存在
     * @param data
     * @param <T>
     * @return
     */
    public static final <T> ResultData directoryNot(Map<String, T> data) {
        return new ResultData(-3, "目录不存在", data);
    }

    /**
     * 文件不存在
     * @param data
     * @param <T>
     * @return
     */
    public static final <T> ResultData fileNot(Map<String, T> data) {
        return new ResultData(-31066, "文件不存在", data);
    }

    /**
     * 测试使用
     * @param data
     * @param <T>
     * @return
     */
    public static final <T> ResultData test(Map<String, T> data) {
        return new ResultData(0, "测试", data);
    }
}
