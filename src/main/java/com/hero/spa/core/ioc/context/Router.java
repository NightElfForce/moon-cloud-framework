package com.hero.spa.core.ioc.context;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author yx
 */
@Data
public class Router {

    private String url;
    private Method method;
    private String reqMethodName;
    private Class<?> clazz;

}
