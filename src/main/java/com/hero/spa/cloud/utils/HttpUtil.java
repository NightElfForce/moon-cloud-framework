package com.hero.spa.cloud.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpUtil {

    public static JSONObject httpGet(String url){
        JSONObject o = new JSONObject();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            String line = null;
            StringBuffer responseSB = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                responseSB.append(line);
            }
            o = JSON.parseObject(responseSB.toString());
            reader.close();
            httpClient.close();
            return o;
        } catch (Exception e) {
            System.out.println("注册异常！");
        }
        return o;
    }
}
