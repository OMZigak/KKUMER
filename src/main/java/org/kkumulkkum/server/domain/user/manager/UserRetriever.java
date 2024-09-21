package org.kkumulkkum.server.domain.user.manager;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.domain.user.User;
import org.kkumulkkum.server.domain.user.Provider;
import org.kkumulkkum.server.common.exception.UserException;
import org.kkumulkkum.server.common.exception.code.UserErrorCode;
import org.kkumulkkum.server.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRetriever {

    private final UserRepository userRepository;

    public User findById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }
  
    public boolean existsByProviderIdAndProvider(
            final String providerId,
            final Provider provider
    ) {
        return userRepository.existsByProviderIdAndProvider(providerId, provider);
    }

    public User findByProviderIdAndProvider(
            final String providerId,
            final Provider provider
    ) {
        return userRepository.findByProviderIdAndProvider(providerId, provider)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND_USER));
    }
}
