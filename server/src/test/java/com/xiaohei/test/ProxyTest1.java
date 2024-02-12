package com.xiaohei.test;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class ProxyTest1 {

    @Test
    public void m() {

    }


}

interface A {
    String ssr(String host);

    void show();
}

class B_A implements A{

    @Override
    public String ssr(String host) {
        return "ssr://" + host;
    }

    @Override
    public void show() {
        System.out.println("OK!!!");
    }
}
