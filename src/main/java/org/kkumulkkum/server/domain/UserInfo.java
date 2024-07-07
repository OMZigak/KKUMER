package org.kkumulkkum.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profileImg;

    private int level;

    private int promiseCount;

    private int tardyCount;

    private Long tardySum;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserInfo(String name,
                    String profileImg,
                    int level,
                    int promiseCount,
                    int tardyCount,
                    User user) {
        this.name = name;
        this.profileImg = profileImg;
        this.level = level;
        this.promiseCount = promiseCount;
        this.tardyCount = tardyCount;
        this.user = user;
        this.tardySum = 0L;
    }
}
