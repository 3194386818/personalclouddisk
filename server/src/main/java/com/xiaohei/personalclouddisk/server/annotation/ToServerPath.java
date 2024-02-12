package com.xiaohei.personalclouddisk.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将客户端的路径转换成服务器能够使用的路径
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToServerPath {
    // 如果他是一个 POJO 的类，就指定他里面某个属性
    // 如果他是一个 String 则不用指定
    String name() default "";
}
