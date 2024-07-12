package org.kkumulkkum.server.service.userInfo;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.UserInfo;
import org.kkumulkkum.server.exception.UserException;
import org.kkumulkkum.server.exception.code.UserErrorCode;
import org.kkumulkkum.server.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoRetriever {

    private final UserInfoRepository userInfoRepository;

    public UserInfo findByUserId(final Long id) {
        return userInfoRepository.findByUserId(id)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }

    public UserInfo findByParticipantId(final Long id) {
        return userInfoRepository.findByParticipantId(id)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }
}
