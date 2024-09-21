package org.kkumulkkum.server.external.config;

import org.kkumulkkum.server.ServerApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = ServerApplication.class)
public class FeignClientConfig {
}
