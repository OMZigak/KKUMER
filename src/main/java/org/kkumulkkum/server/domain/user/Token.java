package org.kkumulkkum.server.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value="token", timeToLive = 60 * 60 * 24 * 14)
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Token {

    @Id
    private Long id;

    @Indexed
    private String refreshToken;

    @Builder
    public Token(Long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }

}
