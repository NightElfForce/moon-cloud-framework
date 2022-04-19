package com.hero.spa.core.ioc;

import com.hero.spa.cloud.feign.annotation.FeignClient;
import com.hero.spa.core.ioc.annotation.Bean;
import com.hero.spa.core.ioc.annotation.SPA;
import com.hero.spa.core.ioc.bean.InitFactory;
import com.hero.spa.core.ioc.context.BeansContext;
import com.hero.spa.core.util.PackageClassLoader;
import com.hero.spa.mvc.annotation.Controller;
import com.hero.spa.orm.annotation.Mapper;
import com.hero.spa.orm.annotation.MySqlConfig;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yx
 */
public class Initialize {

    public static void init(Set<String> packageNames) {
        PackageClassLoader loader = new PackageClassLoader(packageNames);
        Map<Class, List<Class<?>>> packageClass = loader.getAll();
        InitFactory factory = new InitFactory();
        Class[] classes = new Class[]{Bean.class, Controller.class, Mapper.class, MySqlConfig.class, FeignClient.class};
        for (Class clazz : classes) {
            factory.init(clazz).init(packageClass.get(clazz));
        }
    }


    public static void interflow() {
        Map<String, Object> all = BeansContext.getBeans();
        all.forEach((k, v) -> {
            Class aClass = v.getClass();
            while (!aClass.equals(Object.class)) {
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    injection(field, v);
                }
                aClass = aClass.getSuperclass();
            }
        });
    }


    private static void injection(Field declaredField, Object v) {
        Object bean;
        if (declaredField.isAnnotationPresent(SPA.class)) {
            bean = BeansContext.getBean(declaredField.getType().getName());
            Map<String, Object> beans = BeansContext.getBeans();
            List<Class<?>> classes = new ArrayList();
            if (beans.get(declaredField.getType().getName()) == null) {
                beans.forEach((k, c) -> {
                    if (declaredField.getType().isAssignableFrom(c.getClass())) {
                        classes.add(c.getClass());
                    }
                });
            }
            if (classes.size() == 1) {
                bean = BeansContext.getBean(classes.get(0).getName());
            }
            try {
                declaredField.setAccessible(true);
                declaredField.set(v, bean);
            } catch (Exception e) {
                System.out.print("装配异常{}"+ bean);
            }
        }

    }

}
