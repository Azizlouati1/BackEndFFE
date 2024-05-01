package com.cni.elearning.Services;

import com.cni.elearning.Dtos.JwtAuthenticationResponse;
import com.cni.elearning.Dtos.SignInRequest;
import com.cni.elearning.Models.Instructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cni.elearning.Dtos.SignUpRequest;
import com.cni.elearning.Models.Role;
import com.cni.elearning.Models.User;
import com.cni.elearning.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService{
    
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final IJWTService jwtService;


    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setImage(signUpRequest.getImage());
        return userRepository.save(user);
    }
    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Email not found"));
        var jwt = jwtService.generateToken(user,user.getRole());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }
}
