package org.kkumulkkum.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IsMember {
    int meetingIdParamIndex() default -1;
    int promiseIdParamIndex() default -1;
}
