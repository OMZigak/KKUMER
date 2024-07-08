package org.kkumulkkum.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String invitationCode;

    @OneToMany(mappedBy = "meeting")
    private List<Member> members;

    @Builder
    public Meeting(String name, String invitationCode) {
        this.name = name;
        this.invitationCode = invitationCode;
    }
}
