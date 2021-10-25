package com.erp.backend.controller;

import com.erp.backend.config.EmailConfig;
import com.erp.backend.dto.RegisterRequest;
import com.erp.backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.rmi.activation.ActivationException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;
    private EmailConfig emailConfig;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) throws ActivationException, MessagingException {
        authService.register(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity verify(@PathVariable String token){
        authService.verifyToken(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
