package com.hero.spa.core.ioc.bean;

import com.hero.spa.core.ioc.context.BeansContext;
import com.hero.spa.orm.MapperProxy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author yx
 */
public class MapperInit implements Init {
    @Override
    public void init(List<Class<?>> clazz) {
        if (clazz==null){
            return;
        }
        try {
            for (Class aClass : clazz) {
                //mapper接口动态代理为实现类，放入Bean池
                Object o = Proxy.newProxyInstance(aClass.getClassLoader(), new Class<?>[]{aClass}, new MapperProxy<>());
                BeansContext.addBean(aClass.getName(), o);
            }
        } catch (Exception e) {
            System.out.print("初始化Mapper异常");
        }
    }

    @Override
    public void init(Class clazz) {

    }
}
