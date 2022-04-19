package com.hero.spa.orm.statement;

import com.hero.spa.orm.sqlResolver.Resolver;

import java.lang.reflect.Parameter;
/**
 * @author yinx
 */
public interface Statement {

    Object getResult(Resolver resolver, Parameter[] parameters, Object[] args);

}
