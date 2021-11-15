package com.mfptps.appdgessddi.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mfptps.appdgessddi.security.jwt.JWTFilter;
import com.mfptps.appdgessddi.security.jwt.TokenProvider;
import com.mfptps.appdgessddi.service.dto.AuthenticationInformationDto;
import com.mfptps.appdgessddi.web.vm.LoginVM;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

/**
 * Controller to authenticate agents.
 */
@RestController
@RequestMapping("/api")
public class AgentJWTController {
    
    private final Logger log = LoggerFactory.getLogger(AgentJWTController.class);

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AgentJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationInformationDto> authorize(@Valid @RequestBody LoginVM loginVM) {

        log.debug("Login with information {}", loginVM);
        
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        // String jwt = tokenProvider.createToken(authentication, rememberMe);
        AuthenticationInformationDto informationDto = tokenProvider.createCustomAuthToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + informationDto.getAccessToken());
        return new ResponseEntity<>(informationDto, httpHeaders, HttpStatus.OK);
    }

    /* @PostMapping("/auth/info")
    public ResponseEntity<UsernameAuthoritiesDto> authorizationInformation(@RequestBody String token) {
        //UserDetails agentDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = tokenProvider.getAuthentication(token);
        log.debug("Credentials = {}", authentication);
        //UserDetails agentDetails = (UserDetails) authentication.get
        UsernameAuthoritiesDto agentnameAuthoritiesDto = UsernameAuthoritiesDto.builder()
                .agentname(authentication.getName())
                .authorities(getRoles(authentication.getAuthorities()))
                .build();
        return new ResponseEntity<>(agentnameAuthoritiesDto, HttpStatus.OK);
    }

    
    
    private Set<String> getRoles(Collection<? extends GrantedAuthority> grantedAuthoritys) {
        return grantedAuthoritys.stream().map(aut -> aut.getAuthority()).collect(Collectors.toSet());
    } */
}
