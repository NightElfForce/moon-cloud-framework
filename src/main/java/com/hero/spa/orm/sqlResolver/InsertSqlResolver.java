package com.hero.spa.orm.sqlResolver;

import com.hero.spa.core.exception.SpaException;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yinx
 */
public class InsertSqlResolver extends SqlAbstractResolver {

    @Override
    public Resolver resolver(String sqlValue, Parameter[] parameters, Object[] args) {
        Map map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        List<String> params = new ArrayList();
        for (int i = 0; i < sqlValue.length(); i++) {
            char c = sqlValue.charAt(i);
            if (c == '#') {
                if (sqlValue.charAt(i + 1) != '{') {
                    throw new SpaException("sql异常，请检查" + sqlValue);
                }
                StringBuilder p = new StringBuilder();
                {
                    i = validationEnd(p, i, sqlValue);
                    params.add(p.toString());
                    builder.append("?");
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
