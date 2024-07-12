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
import org.kkumulkkum.server.dto.auth.response.UserTokenDto;
import org.kkumulkkum.server.exception.AuthException;
import org.kkumulkkum.server.exception.code.AuthErrorCode;
import org.kkumulkkum.server.service.user.UserRetriever;
import org.kkumulkkum.server.service.user.UserSaver;
import org.kkumulkkum.server.service.userInfo.UserInfoRetriever;
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
    private final TokenRetriever tokenRetriever;
    private final TokenRemover tokenRemover;
    private final UserInfoRetriever userInfoRetriever;

    @Transactional
    public UserTokenDto signin(final String providerToken, final UserLoginDto userLoginDto) {
        SocialUserDto socialUserDto = getSocialInfo(providerToken, userLoginDto);
        User user = loadOrCreateUser(userLoginDto.provider(), socialUserDto);
        UserInfo userInfo = userInfoRetriever.findByUserId(user.getId());
        userInfo.updateFcmToken(userLoginDto.fcmToken());
        JwtTokenDto tokens = jwtTokenProvider.issueTokens(user.getId());
        saveToken(user.getId(), tokens);
        return UserTokenDto.of(userInfo, tokens);
    }

    @Transactional
    public void signout(final Long userId) {
        tokenRemover.removeById(userId);
    }

    @Transactional
    public JwtTokenDto reissue(final String refreshToken) {
        Long userId = jwtTokenProvider.getUserIdFromJwt(refreshToken);
        Token token = tokenRetriever.findByRefreshToken(refreshToken);

        if(!userId.equals(token.getId())) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }

        JwtTokenDto tokens = jwtTokenProvider.issueTokens(userId);
        saveToken(userId, tokens);
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
                    .user(newUser)
                    .build();

            userSaver.save(newUser);
            userInfoSaver.save(newUserInfo);
        }

        return userRetriever.findByProviderIdAndProvider(socialUserDto.platformId(), provider);
    }

    private void saveToken(final Long userId, final JwtTokenDto tokens) {
        tokenSaver.save(
                Token.builder()
                        .id(userId)
                        .refreshToken(tokens.refreshToken())
                        .build()
        );
    }
}
