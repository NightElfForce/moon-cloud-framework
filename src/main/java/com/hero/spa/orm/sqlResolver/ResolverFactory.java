package com.hero.spa.orm.sqlResolver;

import com.hero.spa.orm.annotation.Insert;
import com.hero.spa.orm.annotation.Select;

import java.lang.annotation.Annotation;
/**
 * @author yinx
 */
public class ResolverFactory {

    public SqlAbstractResolver creat(Class<? extends Annotation> annotationClass){
        if (Insert.class.equals(annotationClass)) {
            return new InsertSqlResolver();
        }
        if (Select.class.equals(annotationClass)) {
            return new SelectSqlResolver();
        }
        return null;
    }
}
