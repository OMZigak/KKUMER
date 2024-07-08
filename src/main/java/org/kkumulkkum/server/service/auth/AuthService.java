package org.kkumulkkum.server.service.auth;

import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.auth.jwt.JwtTokenProvider;
import org.kkumulkkum.server.auth.openfeign.kakao.dto.SocialUserDto;
import org.kkumulkkum.server.auth.openfeign.apple.service.AppleService;
import org.kkumulkkum.server.auth.openfeign.kakao.service.KakaoService;
import org.kkumulkkum.server.domain.Token;
import org.kkumulkkum.server.domain.User;
import org.kkumulkkum.server.domain.UserInfo;
import org.kkumulkkum.server.domain.enums.Provider;
import org.kkumulkkum.server.domain.enums.Role;
import org.kkumulkkum.server.dto.auth.request.UserLoginDto;
import org.kkumulkkum.server.dto.auth.response.JwtTokenDto;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.kkumulkkum.server.service.user.UserRetriever;
import org.kkumulkkum.server.service.user.UserSaver;
import org.kkumulkkum.server.service.userInfo.UserInfoSaver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoService kakaoService;
    private final AppleService appleService;
    private final UserRetriever userRetriever;
    private final UserSaver userSaver;
    private final UserInfoSaver userInfoSaver;
    private final TokenSaver tokenSaver;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public JwtTokenDto signin(final String providerToken, final UserLoginDto userLoginDto) {
        SocialUserDto socialUserDto = getSocialInfo(providerToken, userLoginDto);
        User user = loadOrCreateUser(userLoginDto.provider(), socialUserDto);
        JwtTokenDto tokens = jwtTokenProvider.issueTokens(user.getId());
        tokenSaver.save(
                Token.builder()
                        .id(user.getId())
                        .refreshToken(tokens.refreshToken())
                        .build()
        );
        return tokens;
    }

    private SocialUserDto getSocialInfo(final String providerToken, final UserLoginDto userLoginDto) {
        if (userLoginDto.provider().toString().equals("KAKAO")){
            return kakaoService.getSocialUserInfo(providerToken);
        } else if (userLoginDto.provider().toString().equals("APPLE")){
            return appleService.getSocialUserInfo(providerToken);
        } else {
            throw new AuthException(AuthErrorCode.INVALID_PROVIDER);
        }
    }

    private User loadOrCreateUser(final Provider provider, final SocialUserDto socialUserDto){
        boolean isRegistered = userRetriever.existsByProviderIdAndProvider(socialUserDto.platformId(), provider);

        if (!isRegistered){
            User newUser = User.builder()
                    .provider(provider)
                    .providerId(socialUserDto.platformId())
                    .role(Role.USER)
                    .build();

            UserInfo newUserInfo = UserInfo.builder()
                    .email(socialUserDto.email())
                    .build();

            userSaver.save(newUser);
            userInfoSaver.save(newUserInfo);
        }

        return userRetriever.findByProviderIdAndProvider(socialUserDto.platformId(), provider);
    }
}
