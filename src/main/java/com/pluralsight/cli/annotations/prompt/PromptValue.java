package com.pluralsight.cli.annotations.prompt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PromptValue {
    String prompt() default "Enter Value: ";
    PromptType type() default PromptType.STRING;
    boolean nullable() default true;
}
