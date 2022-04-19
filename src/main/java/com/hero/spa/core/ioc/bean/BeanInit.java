package com.hero.spa.core.ioc.bean;

import com.hero.spa.core.ioc.annotation.Bean;
import com.hero.spa.core.ioc.context.BeansContext;

import java.util.List;
/**
 * @author yx
 */
public class BeanInit implements Init{
    @Override
    public void init(List<Class<?>> clazz) {
        if (clazz==null){
            return;
        }
        for (Class aClass : clazz) {
            Bean annotation = (Bean) aClass.getAnnotation(Bean.class);
            String name;
            if (annotation.value().trim().length() > 0) {
                name = annotation.value();
            } else {
                name = aClass.getName();
            }
            try {
                BeansContext.addBean(aClass.getName(), aClass.newInstance());
            } catch (Exception e) {
                System.out.print("初始化@Bean对象异常{}"+ aClass);
            }
        }
    }

    @Override
    public void init(Class clazz) {

    }
}
