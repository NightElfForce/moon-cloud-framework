package com.hero.spa.orm.statement;

import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * @author yinx
 */
public class StatementUtil {

    public static void setValue(PreparedStatement ps, Class clazz, Object vlaue, int i) {
        try {
            if (String.class.equals(clazz)) {
                ps.setString(i, (String) vlaue);
            } else if (Integer.class.equals(clazz)) {
                ps.setInt(i, (Integer) vlaue);
            } else if (int.class.equals(clazz)) {
                ps.setInt(i, (Integer) vlaue);
            } else if (Date.class.equals(clazz)) {
                ps.setDate(i, (Date) vlaue);
            }
        } catch (Exception e) {
            System.out.print("参数解析异常{}"+vlaue);
        }
    }

}
