package org.kkumulkkum.server.external.service.fcm.dto;

import org.kkumulkkum.server.external.service.fcm.FcmContent;

public record FcmMessageDto(
        String title,
        String body,
        String screen,
        Long promiseId
) {
    public static FcmMessageDto of(FcmContent fcmContent, Long promiseId) {
        return new FcmMessageDto(
                fcmContent.getTitle(),
                fcmContent.getBody(),
                fcmContent.getScreen(),
                promiseId
        );
    }
}
