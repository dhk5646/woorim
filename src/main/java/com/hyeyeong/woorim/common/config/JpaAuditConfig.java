package com.hyeyeong.woorim.common.config;

import com.hyeyeong.woorim.common.auditing.AuditorAwareImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditorAware")
@RequiredArgsConstructor
public class JpaAuditConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public AuditorAware<String> auditorAware() { //AuditorAware을 빈으로 등록
        return new AuditorAwareImpl();
    }

}
