package org.kkumulkkum.server.domain.userinfo.manager;

import org.kkumulkkum.server.domain.userinfo.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoEditor {

    public void updateImage(
            final UserInfo userInfo,
            final String imageUrl
    ) {
        userInfo.updateImage(imageUrl);
    }

    public void deleteImage(final UserInfo userInfo) {
        userInfo.deleteImage();
    }

    public void updateName(
            final UserInfo userInfo,
            final String name
    ) {
        userInfo.updateName(name);
    }
}
