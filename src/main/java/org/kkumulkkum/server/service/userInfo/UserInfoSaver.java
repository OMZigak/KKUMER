package org.kkumulkkum.server.service.userInfo;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.userinfo.UserInfo;
import org.kkumulkkum.server.domain.userinfo.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoSaver {

    private final UserInfoRepository userInfoRepository;

    public void save(final UserInfo user){
        userInfoRepository.save(user);
    }

}
