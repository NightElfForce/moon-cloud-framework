package com.hero.spa.orm;

import com.hero.spa.orm.annotation.Delete;
import com.hero.spa.orm.annotation.Insert;
import com.hero.spa.orm.annotation.Select;
import com.hero.spa.orm.annotation.Update;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author yinx
 */
public class MapperProxy<T> implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Parameter[] parameters = method.getParameters();
        Select select = method.getAnnotation(Select.class);
        Insert insert = method.getAnnotation(Insert.class);
        Update update = method.getAnnotation(Update.class);
        Delete delete = method.getAnnotation(Delete.class);
        String sql = null;
        if (select != null) {
            sql = select.value();
            return StatementAdapter.statement(sql, parameters, args, Select.class);
        }
        if (insert != null) {
            sql = insert.value();
            return StatementAdapter.statement(sql, parameters, args, Insert.class);
        }
        if (update != null) {
            sql = update.value();
            return StatementAdapter.statement(sql, parameters, args, Update.class);
        }
        if (delete != null) {
            sql = delete.value();
            return StatementAdapter.statement(sql, parameters, args, Delete.class);
        }
        return null;
    }
}
