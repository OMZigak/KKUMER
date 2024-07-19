package org.kkumulkkum.server.log.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kkumulkkum.server.log.wrapper.CachedBodyRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ServletWrappingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest cachedBodyRequestWrapper = request;

        // 멀티파트 요청이면 원본 요청을 사용
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            filterChain.doFilter(request, response);
        } else {
            // 멀티파트가 아닌 경우에만 캐싱
            cachedBodyRequestWrapper = new CachedBodyRequestWrapper(request);
            filterChain.doFilter(cachedBodyRequestWrapper, response);
        }
    }
}

