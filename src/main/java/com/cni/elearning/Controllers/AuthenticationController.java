package com.cni.elearning.Controllers;

import com.cni.elearning.Dtos.JwtAuthenticationResponse;
import com.cni.elearning.Dtos.RefreshTokenRequest;
import com.cni.elearning.Dtos.SignInRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cni.elearning.Dtos.SignUpRequest;
import com.cni.elearning.Models.User;
import com.cni.elearning.Services.IAuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final IAuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        User newUser = authenticationService.signup(signUpRequest);
        System.out.println(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
