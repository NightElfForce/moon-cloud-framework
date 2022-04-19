package com.hero.spa.orm;

import com.hero.spa.orm.sqlResolver.Resolver;
import com.hero.spa.orm.sqlResolver.ResolverFactory;
import com.hero.spa.orm.sqlResolver.SqlAbstractResolver;
import com.hero.spa.orm.statement.Statement;
import com.hero.spa.orm.statement.StatementFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * @author yinx
 */
public class StatementAdapter {
    public static Object statement(String sqlValue, Parameter[] parameters, Object[] args,Class<? extends Annotation> clazz) {
        ResolverFactory resolverFactory = new ResolverFactory();
        SqlAbstractResolver resolver = resolverFactory.creat(clazz);
        Resolver re = resolver.resolver(sqlValue, parameters, args);
        StatementFactory statementFactory = new StatementFactory();
        Statement statement = statementFactory.creat(clazz);
        Object result = statement.getResult(re,parameters,args);
        return result;
    }
}

