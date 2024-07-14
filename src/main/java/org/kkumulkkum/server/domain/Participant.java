package org.kkumulkkum.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime preparationStartAt;

    private LocalDateTime departureAt;

    private LocalDateTime arrivalAt;

    private int preparationTime;

    private int travelTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promise_id")
    private Promise promise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Participant(
            LocalDateTime preparationStartAt,
            LocalDateTime departureAt,
            LocalDateTime arrivalAt,
            int preparationTime,
            int travelTime,
            Promise promise,
            Member member
    ) {
        this.preparationStartAt = preparationStartAt;
        this.departureAt = departureAt;
        this.arrivalAt = arrivalAt;
        this.preparationTime = preparationTime;
        this.travelTime = travelTime;
        this.promise = promise;
        this.member = member;
    }

    public void preparePromise() {
        this.preparationStartAt = LocalDateTime.now();
    }

    public void departurePromise() {
        this.departureAt = LocalDateTime.now();
    }

    public void arrivalPromise() {
        this.arrivalAt = LocalDateTime.now();
    }

    public void updatePreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void updateTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
}
