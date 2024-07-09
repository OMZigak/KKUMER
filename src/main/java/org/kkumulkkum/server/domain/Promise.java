package org.kkumulkkum.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kkumulkkum.server.domain.enums.DressUpLevel;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Promise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private DressUpLevel dressUpLevel;

    private String penalty;

    private boolean isCompleted;

    private String placeName;

    private Double x;

    private Double y;

    private String address;

    private String roadAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Builder
    public Promise(String name,
                   LocalDateTime time,
                   DressUpLevel dressUpLevel,
                   String penalty,
                   String placeName,
                   Double x,
                   Double y,
                   String address,
                   String roadAddress,
                   Meeting meeting
    ) {
        this.name = name;
        this.time = time;
        this.dressUpLevel = dressUpLevel;
        this.penalty = penalty;
        this.placeName = placeName;
        this.x = x;
        this.y = y;
        this.address = address;
        this.roadAddress = roadAddress;
        this.meeting = meeting;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

}
