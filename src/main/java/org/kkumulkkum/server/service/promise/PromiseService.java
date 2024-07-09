package org.kkumulkkum.server.service.promise;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.Promise;
import org.kkumulkkum.server.dto.promise.response.PromiseDto;
import org.kkumulkkum.server.dto.promise.response.PromisesDto;
import org.kkumulkkum.server.service.member.MemberRetreiver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromiseService {

    private final PromiseRetriever promiseRetriever;

    @Transactional(readOnly = true)
    public PromisesDto getPromises(
            final Long userId,
            final Long meetingId,
            final Boolean done
    ) {
        //TODO: Member 검증
//        memberRetreiver.existsByMeetingIdAndUserId(meetingId, userId);
        List<Promise> promises = promiseRetriever.findAllByMeetingId(meetingId);

        return PromisesDto.of(promises, done);
    }

    @Transactional(readOnly = true)
    public PromiseDto getPromise(
            final Long userId,
            final Long promiseId) {

        //TODO: Member 검증
//        memberRetreiver.existsByMeetingIdAndUserId(meetingId, userId);

        Promise promise = promiseRetriever.findById(promiseId);

        return PromiseDto.from(promise);
    }
}
