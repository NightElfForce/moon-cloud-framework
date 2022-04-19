package com.hero.spa.orm.sqlResolver;

import com.hero.spa.core.exception.SpaException;

import java.lang.reflect.Parameter;

/**
 * @author yinx
 */
public abstract class SqlAbstractResolver {

    public abstract Resolver resolver(String sqlValue, Parameter[] parameters, Object[] args);

    public int validationEnd(StringBuilder str, int i, String sqlValue) {
        int end = 0;
        for (int j = i + 2; j < sqlValue.length(); j++) {
            if (sqlValue.charAt(j) == '%') {
                if(sqlValue.charAt(j+1)=='}'){
                    end=j+1;
                }else {
                    new SpaException("sql语句解析异常"+sqlValue);
                }
                break;
            }
            if(sqlValue.charAt(j)=='}'){
                end=j;
                break;
            }
            str.append(sqlValue.charAt(j));
        }
        return end;
    }


}
