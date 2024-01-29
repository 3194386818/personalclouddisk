package com.xiaohei.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Test1 {

    @Test
    public void test1() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 1);

        System.out.println(map.get("a"));
    }

    @Test
    public void pathsTest() throws IOException {


    }


}
