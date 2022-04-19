package com.hero.spa.core.ioc.annotation;

import java.lang.annotation.*;


/**
 * @author yx
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
    String value() default "";
}
