package com.xiaohei.personalclouddisk.server;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

public class TVT implements BootstrapRegistryInitializer {
    @Override
    public void initialize(BootstrapRegistry registry) {


        System.out.println("启动SpringBoot程序之前");
    }
}
