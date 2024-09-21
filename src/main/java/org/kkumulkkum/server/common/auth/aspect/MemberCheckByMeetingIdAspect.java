package org.kkumulkkum.server.common.auth.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.kkumulkkum.server.common.auth.annotation.IsMemberByMeetingId;
import org.kkumulkkum.server.common.exception.MemberException;
import org.kkumulkkum.server.common.exception.code.MemberErrorCode;
import org.kkumulkkum.server.domain.member.manager.MemberRetreiver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class MemberCheckByMeetingIdAspect {

    private final MemberRetreiver memberRetreiver;

    @Before("@annotation(checkUserInMeeting)")
    public void checkUserInMeeting(JoinPoint joinPoint, IsMemberByMeetingId checkUserInMeeting) throws Throwable {
        Long meetingId = (Long) joinPoint.getArgs()[checkUserInMeeting.meetingIdParamIndex()];
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!memberRetreiver.existsByMeetingIdAndUserId(meetingId, userId)) {
            throw new MemberException(MemberErrorCode.NOT_JOINED_MEMBER);
        }
    }
}
