package com.hyeyeong.woorim.common.auditing;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: 추후 변경 필요
        return Optional.of("sample");
    }
}
