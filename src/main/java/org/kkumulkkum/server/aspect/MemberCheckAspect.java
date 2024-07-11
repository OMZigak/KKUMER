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
        Long meetingId = (Long) args[(int) checkUserInMeeting.meetingIdParamIndex()];
        Long promiseId = (Long) args[(int) checkUserInMeeting.promiseIdParamIndex()];
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (meetingId != -1) {
            if (!memberRetreiver.existsByMeetingIdAndUserId(meetingId, userId)) {
                throw new MemberException(MemberErrorCode.NOT_JOINED_MEMBER);
            }
        }
        if (promiseId != -1) {
            if (!memberRetreiver.existsByPromiseIdAndUserId(promiseId, userId)) {
                throw new MemberException(MemberErrorCode.NOT_JOINED_MEMBER);
            }
        }
    }
}
