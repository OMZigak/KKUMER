package org.kkumulkkum.server.common.auth.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.kkumulkkum.server.common.auth.annotation.IsParticipant;
import org.kkumulkkum.server.common.exception.ParticipantException;
import org.kkumulkkum.server.common.exception.code.ParticipantErrorCode;
import org.kkumulkkum.server.domain.participant.manager.ParticipantRetriever;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ParticipantCheckAspect {

    private final ParticipantRetriever participantRetriever;

    @Before("@annotation(checkUserInPromise)")
    public void checkUserInMeeting(JoinPoint joinPoint, IsParticipant checkUserInPromise) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long promiseId = (Long) args[(int) checkUserInPromise.promiseIdParamIndex()];
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!participantRetriever.existsByPromiseIdAndUserId(promiseId, userId)) {
            throw new ParticipantException(ParticipantErrorCode.NOT_JOINED_PROMISE);
        }
    }
}
