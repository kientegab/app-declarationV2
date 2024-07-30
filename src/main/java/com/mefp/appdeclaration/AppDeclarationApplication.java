package com.mefp.appdeclaration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mefp.appdeclaration.config.ApplicationProperties;
import com.mefp.appdeclaration.repositories.MinistereRepository;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, MailProperties.class})
@EnableJpaRepositories(basePackageClasses = MinistereRepository.class)
public class AppDeclarationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppDeclarationApplication.class, args);
    }

}
