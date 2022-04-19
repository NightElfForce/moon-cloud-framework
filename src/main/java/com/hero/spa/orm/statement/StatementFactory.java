package com.hero.spa.orm.statement;

import com.hero.spa.orm.annotation.Insert;
import com.hero.spa.orm.annotation.Select;

import java.lang.annotation.Annotation;
/**
 * @author yinx
 */
public class StatementFactory {

    public Statement creat(Class<? extends Annotation> annotationClass){
        if (Insert.class.equals(annotationClass)) {
            return new InsertStatement();
        }
        if (Select.class.equals(annotationClass)) {
            return new SelectStatement();
        }
        return null;
    }
}
