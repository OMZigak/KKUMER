package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUserId(Long id);

    @Query("""
            SELECT ui FROM UserInfo ui
            JOIN FETCH Participant p ON ui.user.id = p.member.user.id
            WHERE p.id = :id""")
    Optional<UserInfo> findByParticipantId(Long id);

    void deleteByUserId(Long userId);
}
