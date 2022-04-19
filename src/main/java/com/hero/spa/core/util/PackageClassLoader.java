package com.hero.spa.core.util;

import com.hero.spa.cloud.feign.annotation.FeignClient;
import com.hero.spa.core.ioc.annotation.Bean;
import com.hero.spa.core.ioc.annotation.SPA;
import com.hero.spa.mvc.annotation.Controller;
import com.hero.spa.orm.annotation.Mapper;
import com.hero.spa.orm.annotation.MySqlConfig;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class PackageClassLoader {

    private final static  Map<Class,List<Class<?>>> packageClass = new ConcurrentHashMap();

    public PackageClassLoader(Set<String> packageSet){
        for(String basePackage:packageSet){
           List<Class<?>> classes =  ClassLoadUtil.LoadClasses(basePackage, false);
           classes.forEach(aClass -> {
               if(aClass.getAnnotation(Bean.class)!=null){
                   add(Bean.class,aClass);
               }
               if(aClass.getAnnotation(SPA.class)!=null){
                   add(SPA.class,aClass);
               }
               if(aClass.getAnnotation(Controller.class)!=null){
                   add(Controller.class,aClass);
               }
               if(aClass.getAnnotation(Mapper.class)!=null){
                   add(Mapper.class,aClass);
               }
               if(aClass.getAnnotation(MySqlConfig.class)!=null){
                   add(MySqlConfig.class,aClass);
               }
               if(aClass.getAnnotation(FeignClient.class)!=null){
                   add(FeignClient.class,aClass);
               }
            });
        }
    }

    public void add(Class<? extends Annotation> annotationClass,Class<?> aclass){
        List<Class<?>> list = new ArrayList<>();
        if(packageClass.get(annotationClass)!=null){
            list = packageClass.get(annotationClass);
        }
        list.add(aclass);
        packageClass.put(annotationClass,list);
    }


    public  Map<Class,List<Class<?>>> getAll(){
        return packageClass;
    }



}
