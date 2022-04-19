package com.hero.spa.core.ioc.bean;

import com.hero.spa.core.ioc.context.BeansContext;
import com.hero.spa.orm.annotation.MySqlConfig;

import java.util.List;

/**
 * @author yx
 */
public class MySqlInit implements Init {
    @Override
    public void init(List<Class<?>> clazz) {
        if (clazz==null){
            return;
        }
        for (Class aClass : clazz) {
            MySqlConfig annotation = (MySqlConfig) aClass.getAnnotation(MySqlConfig.class);
            try {
                BeansContext.addBean("MySqlConfig", aClass.newInstance());
            } catch (Exception e) {
                System.out.print("初始化@Bean对象异常{}"+ aClass);
            }
        }
    }

    @Override
    public void init(Class clazz) {

    }
}
