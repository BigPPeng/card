package com.card.test.ano;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAn {
    String value() default "";
    int role() default 1;
    int max() default 1000;
    int min() default 1;
}
