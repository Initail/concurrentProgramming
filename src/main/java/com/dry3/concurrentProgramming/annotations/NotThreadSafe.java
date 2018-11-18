package com.dry3.concurrentProgramming.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This is Thread Not Safe [CLASS, METHOD] identifying.
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 9:33
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

    String value() default "";
}
