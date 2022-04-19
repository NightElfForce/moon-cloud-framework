package com.hero.spa.core.ioc.annotation;

import java.lang.annotation.*;


/**
 * @author yx
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPABoot {
    String value() default "";
}
