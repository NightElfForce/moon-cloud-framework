package com.hero.spa.orm.annotation;

import java.lang.annotation.*;

/**
 * @author yinx
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MySqlConfig {

    String value() default "";

    String name() default "";

}
