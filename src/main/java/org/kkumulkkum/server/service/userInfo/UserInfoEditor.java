package org.kkumulkkum.server.service.userInfo;

import org.kkumulkkum.server.domain.UserInfo;
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
