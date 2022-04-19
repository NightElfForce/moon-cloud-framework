package com.hero.spa.mvc.annotation;

import java.lang.annotation.*;


/**
 * @author yinx
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GET {
    String value();
}
