package org.kkumulkkum.server.domain.participant;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kkumulkkum.server.domain.promise.Promise;
import org.kkumulkkum.server.domain.base.BaseTimeEntity;
import org.kkumulkkum.server.domain.member.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"promise_id", "member_id"})
        }
)
public class Participant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime preparationStartAt;

    private LocalDateTime departureAt;

    private LocalDateTime arrivalAt;

    private Integer preparationTime;

    private Integer travelTime;

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
            Integer preparationTime,
            Integer travelTime,
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

    public void updatePreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void updateTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }
}
