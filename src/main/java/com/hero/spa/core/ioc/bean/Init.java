package com.hero.spa.core.ioc.bean;

import java.util.List;

public interface Init {

    void init(List<Class<?>> clazz);

    void init(Class clazz);
}
