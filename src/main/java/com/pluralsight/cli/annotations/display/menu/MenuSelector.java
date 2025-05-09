package com.pluralsight.cli.annotations.display.menu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MenuSelector {
    String value() default "\033[47;30mEnter Selection\033[0m> ";
}
