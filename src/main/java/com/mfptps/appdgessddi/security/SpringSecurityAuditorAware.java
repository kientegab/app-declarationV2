package com.mfptps.appdgessddi.security;

import java.util.Optional;

import com.mfptps.appdgessddi.config.Constants;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserMatricule().orElse(Constants.SYSTEM_ACCOUNT));
    }
}
