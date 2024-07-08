package org.kkumulkkum.server.service.user;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.User;
import org.kkumulkkum.server.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSaver {

    private final UserRepository userRepository;

    public User save(final User user){
        return userRepository.save(user);
    }

}
