package com.pluralsight.cli.annotations.prompt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PromptValue {
    String prompt() default "Enter Value: ";
    Class<?> targetClass() default String.class;
    String parserMethod() default "";
    boolean nullable() default true;
}
