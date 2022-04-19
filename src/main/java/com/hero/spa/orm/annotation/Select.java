package com.hero.spa.orm.annotation;

import java.lang.annotation.*;

/**
 * @author yinx
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Select {

    String value();
}
