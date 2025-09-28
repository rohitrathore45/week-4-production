package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//        get the security context
//        get authentication
//        get the principle
//        get the username
        return Optional.of("rohit");
    }
}
