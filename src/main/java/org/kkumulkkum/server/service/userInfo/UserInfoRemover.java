package org.kkumulkkum.server.service.userInfo;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.userinfo.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoRemover {

    private final UserInfoRepository userInfoRepository;

    public void deleteByUserId(final Long userId) {
        userInfoRepository.deleteByUserId(userId);
    }
}
