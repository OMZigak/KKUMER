package org.kkumulkkum.server.service.user;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.User;
import org.kkumulkkum.server.domain.enums.Provider;
import org.kkumulkkum.server.exception.UserException;
import org.kkumulkkum.server.exception.code.UserErrorCode;
import org.kkumulkkum.server.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRetriever {

    private final UserRepository userRepository;

    public boolean existsByProviderIdAndProvider(final String providerId, final Provider provider) {
        return userRepository.existsByProviderIdAndProvider(providerId, provider);
    }

    public User findByProviderIdAndProvider(final String providerId, Provider provider) {
        return userRepository.findByProviderIdAndProvider(providerId, provider)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }
}
