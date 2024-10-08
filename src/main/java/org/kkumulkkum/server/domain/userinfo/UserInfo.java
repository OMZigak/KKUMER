package org.kkumulkkum.server.domain.userinfo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kkumulkkum.server.domain.base.BaseTimeEntity;
import org.kkumulkkum.server.domain.user.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fcmToken;

    private String name;

    private String profileImg;

    private String email;

    private int level;

    private int promiseCount;

    private int tardyCount;

    private Long tardySum;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserInfo(
            String name,
            String profileImg,
            String email,
            User user
    ) {
        this.name = name;
        this.profileImg = profileImg;
        this.email = email;
        this.level = 1;
        this.promiseCount = 0;
        this.tardyCount = 0;
        this.user = user;
        this.tardySum = 0L;
    }


    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void updateImage(String imageUrl) {
        this.profileImg = imageUrl;
    }

    public void deleteImage() {
        this.profileImg = null;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void addPromiseCount() {
        this.promiseCount++;
    }

    public void addLateCount() {
        this.tardyCount++;
    }

    public void addLateTime(long time) {
        this.tardySum += time;
    }

    public void levelUp() {
        int count = this.promiseCount - this.tardyCount;
        if (count == 2) {
            this.level = 2;
        } else if (count == 5) {
            this.level = 3;
        } else if (count == 10) {
            this.level = 4;
        }
    }
}
