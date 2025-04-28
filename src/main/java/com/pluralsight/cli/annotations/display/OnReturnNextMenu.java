package com.pluralsight.cli.annotations.display;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnReturnNextMenu {
    String menuID() default "";
    boolean onNull() default false;
    boolean gotoResult() default false;
    boolean initNextResult() default false; //WIP
    String onFailPrint() default "";
}
