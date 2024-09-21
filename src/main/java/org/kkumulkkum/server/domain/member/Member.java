package org.kkumulkkum.server.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kkumulkkum.server.domain.user.User;
import org.kkumulkkum.server.domain.base.BaseTimeEntity;
import org.kkumulkkum.server.domain.meeting.Meeting;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Builder
    public Member(User user, Meeting meeting) {
        this.user = user;
        this.meeting = meeting;
    }
}
