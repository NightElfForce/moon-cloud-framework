package com.hero.spa.orm.sqlResolver;

import com.hero.spa.core.exception.SpaException;

import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author yinx
 */
public class SelectSqlResolver extends SqlAbstractResolver {

    @Override
    public Resolver resolver(String sqlValue, Parameter[] parameters, Object[] args) {
        Map map = new HashMap<>();
        for(int i=0;i< parameters.length;i++){
            map.put(parameters[i].getName(), args[i]);
        }
        StringBuilder builder = new StringBuilder();
        List<String> params = new ArrayList();
        for(int i=0;i<sqlValue.length();i++){
            char c = sqlValue.charAt(i);
            if(c=='#'){
                if(sqlValue.charAt(i+1)!='{'){
                    throw new SpaException("sql异常，请检查"+sqlValue);
                }
                StringBuilder p  = new StringBuilder();
                if(sqlValue.charAt(i+2)=='%'){
                    i = validationEnd(p,i+1,sqlValue);
                    String param = map.get(p.toString()).toString();
                    params.add(p.toString());
                    if(sqlValue.charAt(i-1)=='%'){
                        builder.append("'%").append(param).append("%'");
                    }else {
                        builder.append("'%").append(param).append("'");
                    }
                }else {
                    i = validationEnd(p,i,sqlValue);
                    String param = map.get(p.toString()).toString();
                    params.add(p.toString());
                    if(sqlValue.charAt(i-1)=='%'){
                        builder.append("'").append(param).append("%'");
                    }else {
                        builder.append("'").append(param).append("'");
                    }
                }
                continue;
            }
            builder.append(c);
        }
        Resolver resolver = new Resolver();
        resolver.setSql(builder.toString());
        resolver.setParam(params);
        return resolver;
    }

}
