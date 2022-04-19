package com.hero.spa.cloud.feign.annotation;

import java.lang.annotation.*;


/**
 * @author yx
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignClient {
    String value() default "";
}
