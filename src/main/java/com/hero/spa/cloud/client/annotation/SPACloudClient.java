package com.hero.spa.cloud.client.annotation;

import java.lang.annotation.*;


/**
 * @author yx
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPACloudClient {
    String name() default "";
    String url() default "";
    String port() default "";
}
