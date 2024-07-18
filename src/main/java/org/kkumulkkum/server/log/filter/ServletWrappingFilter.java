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

        CachedBodyRequestWrapper cachedBodyRequestWrapper = new CachedBodyRequestWrapper(request);
        filterChain.doFilter(cachedBodyRequestWrapper, response);
    }
}