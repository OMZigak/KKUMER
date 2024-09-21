package org.kkumulkkum.server.domain.user.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.user.User;
import org.kkumulkkum.server.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRemover {

    private final UserRepository userRepository;

    public void delete(User user) {
        userRepository.delete(user);
    }
}
