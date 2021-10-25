package com.erp.backend.service;

import com.erp.backend.dto.RegisterRequest;
import com.erp.backend.exception.ActivationException;
import com.erp.backend.models.AccountVerificationToken;
import com.erp.backend.models.NotificationEmail;

import com.erp.backend.models.User;
import com.erp.backend.repository.TokenRepository;
import com.erp.backend.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.erp.backend.config.Constants.EMAIL_ACTIVATION;

@Service
@AllArgsConstructor
public class AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TokenRepository tokenRepository;
    MailService mailService;
    MailBuilder mailBuilder;

    @Transactional
    public void register(RegisterRequest registerRequest) throws MessagingException {
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setCreationDate(Instant.now());
        user.setAccountStatus(false);

        userRepository.save(user);

        String token = generateToken(user); //Generar token
        String message = mailBuilder.build("Welcome to LeoSamERP " + user.getUserName() + "!" +
                " Please visit the link below to activate you account: " + EMAIL_ACTIVATION + "/" + token);
        mailService.sendEmail(new NotificationEmail("Please Activate Your Account", user.getEmail(), message));
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + principal.getUsername()));
    }


    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateToken(User user) { //Saving the token of a user
        String token = UUID.randomUUID().toString();
        AccountVerificationToken verificationToken = new AccountVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        tokenRepository.save(verificationToken);
        return token;
    }

    public void verifyToken(String token) {
        Optional<AccountVerificationToken> verificationToken = tokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new ActivationException("Invalid Activation Token"));
        enableAccount(verificationToken.get());
    }

    public void enableAccount(AccountVerificationToken token) {
        String username = token.getUser().getUserName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ActivationException("User not found with username: " + username));
        user.setAccountStatus(true);
        userRepository.save(user);
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
