package com.hero.spa.core.ioc.bean;

import com.hero.spa.cloud.feign.annotation.FeignClient;
import com.hero.spa.core.ioc.annotation.Bean;
import com.hero.spa.mvc.annotation.Controller;
import com.hero.spa.orm.annotation.Mapper;
import com.hero.spa.orm.annotation.MySqlConfig;

import java.lang.annotation.Annotation;

public class InitFactory {

    public Init init(Class<? extends Annotation> annotationClass){
        if (Bean.class.equals(annotationClass)) {
            return new BeanInit();
        }
        if (Controller.class.equals(annotationClass)) {
            return new ControllerInit();
        }
        if (Mapper.class.equals(annotationClass)) {
            return new MapperInit();
        }
        if (MySqlConfig.class.equals(annotationClass)) {
            return new MySqlInit();
        }
        if (FeignClient.class.equals(annotationClass)) {
            return new FeignInit();
        }
        return null;
    }





}
