package com.hero.spa.cloud.feign.core;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class FeignInfoContext {
    private static Map<String,String> feignInfo = new ConcurrentHashMap<>(16);

    public static String getfeignInfo(String name) {
        return feignInfo.get(name);
    }

    public static String addfeignInfo(String className,String serviceName) {
        return feignInfo.put(className,serviceName);
    }
}
