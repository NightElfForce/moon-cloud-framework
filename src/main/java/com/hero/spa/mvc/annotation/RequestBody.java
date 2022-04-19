package com.hero.spa.mvc.annotation;

import java.lang.annotation.*;


/**
 * @author yinx
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBody {
}
