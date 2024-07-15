package org.kkumulkkum.server.external;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.exception.BusinessException;
import org.kkumulkkum.server.exception.FirebaseException;
import org.kkumulkkum.server.exception.code.BusinessErrorCode;
import org.kkumulkkum.server.exception.code.FirebaseErrorCode;
import org.kkumulkkum.server.external.dto.FcmMessageDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmService {

    @Async
    public void sendBulk(
            final List<String> fcmTokens,
            final FcmMessageDto fcmMessageDto
    ){
        MulticastMessage message = createBulkMessage(fcmTokens, fcmMessageDto);
        try {
            FirebaseMessaging.getInstance().sendMulticast(message);
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
                .putData("screen", fcmMessageDto.screen())
                .putData("promiseId", fcmMessageDto.promiseId().toString())
                .build();
    }
}

