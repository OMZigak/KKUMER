package org.kkumulkkum.server.common.auth.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.kkumulkkum.server.common.auth.annotation.IsMemberByPromiseId;
import org.kkumulkkum.server.common.exception.MemberException;
import org.kkumulkkum.server.common.exception.code.MemberErrorCode;
import org.kkumulkkum.server.domain.member.manager.MemberRetreiver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MemberCheckByPromiseIdAspect {

    private final MemberRetreiver memberRetreiver;

    @Before("@annotation(checkUserInMeeting)")
    public void checkUserInMeeting(JoinPoint joinPoint, IsMemberByPromiseId checkUserInMeeting) throws Throwable {
        Long promiseId = (Long) joinPoint.getArgs()[checkUserInMeeting.promiseIdParamIndex()];
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!memberRetreiver.existsByPromiseIdAndUserId(promiseId, userId)) {
            throw new MemberException(MemberErrorCode.NOT_JOINED_MEMBER);
        }
    }
}
