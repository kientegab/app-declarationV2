package com.mfptps.appdgessddi.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mfptps.appdgessddi.entities.Agent;
import com.mfptps.appdgessddi.repositories.AgentRepository;
import com.mfptps.appdgessddi.security.SecurityUtils;
import com.mfptps.appdgessddi.service.AgentService;
import com.mfptps.appdgessddi.service.MailService;
import com.mfptps.appdgessddi.service.dto.AgentDTO;
import com.mfptps.appdgessddi.service.dto.PasswordChangeDTO;
import com.mfptps.appdgessddi.web.exceptions.InvalidPasswordException;
import com.mfptps.appdgessddi.web.vm.KeyAndPasswordVM;
import com.mfptps.appdgessddi.web.vm.ManagedAgentVM;

import java.util.*;

/**
 * REST controller for managing the current agent's account.
 */
@RestController
@RequestMapping("/api")
public class AccountController {

    private static class AccountControllerException extends RuntimeException {
        private AccountControllerException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AgentRepository agentRepository;

    private final AgentService agentService;

    private final MailService mailService;

    public AccountController(AgentRepository agentRepository, AgentService agentService, MailService mailService) {
        this.agentRepository = agentRepository;
        this.agentService = agentService;
        this.mailService = mailService;
    }

    /**
     * {@code POST  /register} : register the agent.
     *
     * @param managedAgentVM the managed agent View Model.
     * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password
     *                                   is incorrect.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the matricule
     *                                   is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedAgentVM managedAgentVM) {
        if (!checkPasswordLength(managedAgentVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        Agent agent = agentService.registerAgent(managedAgentVM, managedAgentVM.getPassword());
        if (null != managedAgentVM.getEmail() && !managedAgentVM.getEmail().isEmpty()) {
            mailService.sendActivationEmail(agent);
        }
    }

    /**
     * {@code GET  /activate} : activate the registered agent.
     *
     * @param key the activation key.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the agent
     *                          couldn't be activated.
     */
    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        Optional<Agent> agent = agentService.activateRegistration(key);
        if (!agent.isPresent()) {
            throw new AccountControllerException("No agent was found for this activation key");
        }
    }

    /**
     * {@code GET  /authenticate} : check if the agent is authenticated, and return
     * its matricule.
     *
     * @param request the HTTP request.
     * @return the matricule if the agent is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current agent is authenticated");
        return request.getRemoteUser();
    }

    /**
     * {@code GET  /account} : get the current agent.
     *
     * @return the current agent.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the agent
     *                          couldn't be returned.
     */
    @GetMapping("/account")
    public AgentDTO getAccount() {
        return agentService.getAgentWithProfiles()
                .orElseThrow(() -> new AccountControllerException("Agent could not be found"));
    }

    /**
     * {@code POST  /account} : update the current agent information.
     *
     * @param agentDTO the current agent information.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the agent
     *                          matricule wasn't found.
     */
    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody AgentDTO agentDTO) {
        String agentMatricule = SecurityUtils.getCurrentUserMatricule()
                .orElseThrow(() -> new AccountControllerException("Current agent matricule not found"));

        Optional<Agent> agent = agentRepository.findOneByMatricule(agentMatricule);
        if (!agent.isPresent()) {
            throw new AccountControllerException("Agent could not be found");
        }
        agentService.updateAgent(agentDTO.getNom(), agentDTO.getPrenom(), agentDTO.getEmail());
    }

    /**
     * {@code POST  /account/change-password} : changes the current agent's
     * password.
     *
     * @param passwordChangeDto current and new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new
     *                                  password is incorrect.
     */
    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        agentService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    /**
     * {@code POST   /account/reset-password/finish} : Finish to reset the password
     * of the agent.
     *
     * @param keyAndPassword the generated key and the new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is
     *                                  incorrect.
     * @throws RuntimeException         {@code 500 (Internal Server Error)} if the
     *                                  password could not be reset.
     */
    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        Optional<Agent> agent = agentService.completePasswordReset(keyAndPassword.getNewPassword(),
                keyAndPassword.getKey());

        if (!agent.isPresent()) {
            throw new AccountControllerException("No agent was found for this reset key");
        }
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) && password.length() >= ManagedAgentVM.PASSWORD_MIN_LENGTH
                && password.length() <= ManagedAgentVM.PASSWORD_MAX_LENGTH;
    }
}
