package org.kkumulkkum.server.common.auth.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression="T(org.kkumulkkum.server.common.auth.jwt.JwtTokenProvider).checkPrincipal(#this)")
public @interface UserId {
}
