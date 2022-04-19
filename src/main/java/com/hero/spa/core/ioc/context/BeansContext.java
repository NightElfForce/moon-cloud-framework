package com.hero.spa.core.ioc.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class BeansContext {
    private static Map<String,Object> beans = new ConcurrentHashMap<>(32);

    public  static void addBean(String name, Object object) {
        beans.put(name,object);
    }

    public static Map<String,Object> getBeans() {
        return beans;
    }

    public static Object getBean(String name) {
        return beans.get(name);
    }
}
