package com.mefp.appdeclaration.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import com.mefp.appdeclaration.aop.LoggingAspect;
import com.mefp.appdeclaration.utils.AppProfile;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile(AppProfile.DEVELOPMENT)
    public LoggingAspect loggingAspect(Environment env) {
        return new LoggingAspect(env);
    }
}
