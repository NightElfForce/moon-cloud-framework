package com.hero.spa.core.ioc.bean;

import com.hero.spa.cloud.feign.annotation.FeignClient;
import com.hero.spa.cloud.feign.core.FeignInfoContext;
import com.hero.spa.cloud.feign.core.FeignProxy;
import com.hero.spa.core.ioc.context.BeansContext;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author yx
 */
public class FeignInit implements Init {
    @Override
    public void init(List<Class<?>> clazz) {
        if (clazz==null){
            return;
        }
        try {
            for (Class aClass : clazz) {
                FeignClient feignClient = (FeignClient)aClass.getAnnotation(FeignClient.class);
                Object o = Proxy.newProxyInstance(aClass.getClassLoader(), new Class<?>[]{aClass}, new FeignProxy<>());
                BeansContext.addBean(aClass.getName(), o);
                FeignInfoContext.addfeignInfo(aClass.getName(),feignClient.value());
            }
        } catch (Exception e) {
            System.out.print("初始化Feign异常");
        }
    }

    @Override
    public void init(Class clazz) {

    }
}
