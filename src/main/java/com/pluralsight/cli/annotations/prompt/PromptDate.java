package com.pluralsight.cli.annotations.prompt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PromptDate {
    String prompt() default "Enter Date: ";
    String format() default "yyyy-MM-dd";
    boolean nullable() default true;
    boolean askAltFormat() default false;

}
