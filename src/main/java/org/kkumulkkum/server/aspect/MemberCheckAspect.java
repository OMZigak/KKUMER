package org.kkumulkkum.server.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.kkumulkkum.server.annotation.IsMember;
import org.kkumulkkum.server.exception.MemberException;
import org.kkumulkkum.server.exception.code.MemberErrorCode;
import org.kkumulkkum.server.service.member.MemberRetreiver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MemberCheckAspect {

    private final MemberRetreiver memberRetreiver;

    @Before("@annotation(checkUserInMeeting)")
    public void checkUserInMeeting(JoinPoint joinPoint, IsMember checkUserInMeeting) throws Throwable {
        Object[] args = joinPoint.getArgs();
        int meetingIdParamIndex = checkUserInMeeting.meetingIdParamIndex();
        int promiseIdParamIndex = checkUserInMeeting.promiseIdParamIndex();
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (meetingIdParamIndex != -1) {
            if (!memberRetreiver.existsByMeetingIdAndUserId((Long) args[meetingIdParamIndex], userId)) {
                throw new MemberException(MemberErrorCode.NOT_JOINED_MEMBER);
            }
        }
        if (promiseIdParamIndex != -1) {
            if (!memberRetreiver.existsByPromiseIdAndUserId((Long) args[promiseIdParamIndex], userId)) {
                throw new MemberException(MemberErrorCode.NOT_JOINED_MEMBER);
            }
        }
    }
}
