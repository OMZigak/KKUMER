package org.kkumulkkum.server.common.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.kkumulkkum.server.common.auth.authentication.UserAuthentication;
import org.kkumulkkum.server.common.auth.jwt.JwtTokenProvider;
import org.kkumulkkum.server.common.auth.jwt.JwtTokenValidator;
import org.kkumulkkum.server.common.auth.constant.AuthConstant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String token = getJwtFromRequest(request);
        if (StringUtils.hasText(token)) {
            jwtTokenValidator.validateAccessToken(token);
            Long userId = jwtTokenProvider.getUserIdFromJwt(token);
            UserAuthentication authentication = UserAuthentication.createUserAuthentication(userId);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AuthConstant.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AuthConstant.BEARER_TOKEN_PREFIX)) {
            return bearerToken.substring(AuthConstant.BEARER_TOKEN_PREFIX.length());
        }
        return null;
    }
}
