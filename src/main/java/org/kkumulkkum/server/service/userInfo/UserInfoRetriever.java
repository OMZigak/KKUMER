package org.kkumulkkum.server.service.userInfo;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.userinfo.UserInfo;
import org.kkumulkkum.server.exception.UserException;
import org.kkumulkkum.server.exception.code.UserErrorCode;
import org.kkumulkkum.server.domain.userinfo.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoRetriever {

    private final UserInfoRepository userInfoRepository;

    public UserInfo findByUserId(final Long userId) {
        return userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }

    public UserInfo findByParticipantId(final Long participantId) {
        return userInfoRepository.findByParticipantId(participantId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }
}
