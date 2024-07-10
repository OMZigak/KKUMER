package org.kkumulkkum.server.service.userInfo;

import org.kkumulkkum.server.domain.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoEditor {

    public void updateImage(UserInfo userInfo, String imageUrl) {
        userInfo.updateImage(imageUrl);
    }

    public void deleteImage(UserInfo userInfo) {
        userInfo.deleteImage();
    }

    public void updateName(UserInfo userInfo, String name) {
        userInfo.updateName(name);
    }
}
