package org.kkumulkkum.server.config;

import org.kkumulkkum.server.log.filter.ServletWrappingFilter;
import org.kkumulkkum.server.log.filter.MDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<ServletWrappingFilter> secondFilter() {
        FilterRegistrationBean<ServletWrappingFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new ServletWrappingFilter());
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MDCFilter> thirdFilter() {
        FilterRegistrationBean<MDCFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new MDCFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
