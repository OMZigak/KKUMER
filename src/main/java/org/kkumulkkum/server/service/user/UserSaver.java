package org.kkumulkkum.server.service.user;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.user.User;
import org.kkumulkkum.server.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSaver {

    private final UserRepository userRepository;

    public void save(final User user){
        userRepository.save(user);
    }

}
