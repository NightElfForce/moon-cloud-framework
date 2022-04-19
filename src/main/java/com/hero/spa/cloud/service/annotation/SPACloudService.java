package com.hero.spa.cloud.service.annotation;

import java.lang.annotation.*;


/**
 * @author yx
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPACloudService {
    String value() default "";
}
