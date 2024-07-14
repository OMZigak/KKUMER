package org.kkumulkkum.server.external;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.external.dto.FcmMessageDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmService {

    public void sendBulk(
            final List<String> fcmTokens,
            final FcmMessageDto fcmMessageDto
    ){
        MulticastMessage message = createBulkMessage(fcmTokens, fcmMessageDto);
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            log.info("Successfully sent message to multiple devices: Success={} Failure={}",
                    response.getSuccessCount(), response.getFailureCount());
        } catch (FirebaseMessagingException e){
            log.error("Failed to send message. Error: {}", e.getMessage());
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

