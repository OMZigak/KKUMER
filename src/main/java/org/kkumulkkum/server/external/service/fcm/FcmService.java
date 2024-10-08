package org.kkumulkkum.server.external.service.fcm;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.common.exception.FirebaseException;
import org.kkumulkkum.server.common.exception.code.FirebaseErrorCode;
import org.kkumulkkum.server.external.service.fcm.dto.FcmMessageDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmService {

    private final String FCM_DATA_SCREEN = "screen";
    private final String FCM_DATA_PROMISE_ID = "promiseId";

    @Async
    public void sendBulk(
            final List<String> fcmTokens,
            final FcmMessageDto fcmMessageDto
    ){
        MulticastMessage message = createBulkMessage(fcmTokens, fcmMessageDto);
        try {
            FirebaseMessaging.getInstance().sendEachForMulticast(message);
        } catch (FirebaseMessagingException e){
            throw new FirebaseException(FirebaseErrorCode.FCM_ERROR);
        }
    }

    private MulticastMessage createBulkMessage(
            final List<String> fcmTokens,
            final FcmMessageDto fcmMessageDto
    ){
        return MulticastMessage.builder()
                .addAllTokens(fcmTokens)
                .setNotification(
                        Notification.builder()
                                .setTitle(fcmMessageDto.title())
                                .setBody(fcmMessageDto.body())
                                .build()
                )
                .putData(FCM_DATA_SCREEN, fcmMessageDto.screen())
                .putData(FCM_DATA_PROMISE_ID, fcmMessageDto.promiseId().toString())
                .build();
    }
}

