package com.SamuelFumeroHdez.backend.controller;

import com.SamuelFumeroHdez.backend.dto.RegisterRequest;
import com.SamuelFumeroHdez.backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.activation.ActivationException;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) throws ActivationException {
        authService.register(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity verify(@PathVariable String token){
        System.out.printf("Esta llegando");
        authService.verifyToken(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
