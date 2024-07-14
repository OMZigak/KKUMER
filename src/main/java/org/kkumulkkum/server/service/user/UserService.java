package org.kkumulkkum.server.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.domain.UserInfo;
import org.kkumulkkum.server.dto.user.request.ImageUpdateDto;
import org.kkumulkkum.server.dto.user.request.NameUpdateDto;
import org.kkumulkkum.server.dto.user.response.UserDto;
import org.kkumulkkum.server.dto.user.response.UserNameDto;
import org.kkumulkkum.server.exception.AwsException;
import org.kkumulkkum.server.exception.code.AwsErrorCode;
import org.kkumulkkum.server.external.S3Service;
import org.kkumulkkum.server.service.userInfo.UserInfoEditor;
import org.kkumulkkum.server.service.userInfo.UserInfoRetriever;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRetriever userInfoRetriever;
    private final S3Service s3Service;
    private final UserInfoEditor userInfoEditor;

    static final String PROFILE_S3_UPLOAD_FOLDER = "profile/";

    @Transactional
    public void updateImage(
            final Long userId,
            final ImageUpdateDto imageUpdateDto
    ) {
        UserInfo userInfo = userInfoRetriever.findByUserId(userId);

        if (userInfo.getProfileImg() != null) {
            try {
                s3Service.deleteImage(userInfo.getProfileImg());
            } catch (AwsException e) {
                throw new AwsException(e.getErrorCode());
            } catch (RuntimeException | IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        try {
            userInfoEditor.updateImage(
                    userInfo, s3Service.uploadImage(PROFILE_S3_UPLOAD_FOLDER, imageUpdateDto.image())
            );
        } catch (AwsException e) {
            throw new AwsException(e.getErrorCode());
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteImage(final Long userId) {
        UserInfo userInfo = userInfoRetriever.findByUserId(userId);

        if (userInfo.getProfileImg() == null) {
            throw new AwsException(AwsErrorCode.NOT_FOUND_IMAGE);
        }

        try {
            s3Service.deleteImage(userInfo.getProfileImg());
        } catch (AwsException e) {
            throw new AwsException(e.getErrorCode());
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        userInfoEditor.deleteImage(userInfo);
    }

    @Transactional
    public UserNameDto updateName(
            final Long userId,
            final NameUpdateDto nameUpdateDto
    ) {
        UserInfo userInfo = userInfoRetriever.findByUserId(userId);
        userInfoEditor.updateName(userInfo, nameUpdateDto.name());

        return UserNameDto.from(userInfo);
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfo(final Long userId) {
        UserInfo userInfo = userInfoRetriever.findByUserId(userId);
        return UserDto.from(userInfo);
    }
}
