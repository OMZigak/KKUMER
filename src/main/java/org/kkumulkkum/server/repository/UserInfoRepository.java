package org.kkumulkkum.server.repository;

import org.kkumulkkum.server.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
