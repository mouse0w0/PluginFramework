package com.github.mouse0w0.pluginframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
    /**
     * @return plugin id
     */
    String id();

    String version();

    String name() default "";

    String description() default "";

    String[] authors() default {};

    String[] befores() default {};

    String[] afters() default {};

    String[] requires() default {};
}
