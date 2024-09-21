package org.kkumulkkum.server.common.auth.filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kkumulkkum.server.common.exception.AuthException;
import org.kkumulkkum.server.common.exception.code.AuthErrorCode;
import org.kkumulkkum.server.common.exception.code.BusinessErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthException e) {
//            log.error("FilterException throw AuthException : {}", e.getErrorCode().getMessage());
            request.setAttribute("exception", e.getErrorCode());
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
//            log.error("FilterException throw JwtException Exception : {}", e.getMessage());
            request.setAttribute("exception", AuthErrorCode.UNKNOWN_TOKEN);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("FilterException throw Exception : {}", e.toString());
            request.setAttribute("exception", BusinessErrorCode.INTERNAL_SERVER_ERROR);
            filterChain.doFilter(request, response);
        }
    }
}
