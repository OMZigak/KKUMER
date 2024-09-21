package org.kkumulkkum.server.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kkumulkkum.server.domain.base.BaseTimeEntity;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(
            Long id,
            Provider provider,
            String providerId,
            Role role
    ) {
        this.id = id;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }
}
