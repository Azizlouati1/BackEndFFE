package com.cni.elearning.Services.Security;

import com.cni.elearning.Dtos.JwtAuthenticationResponse;
import com.cni.elearning.Dtos.SignInRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cni.elearning.Dtos.SignUpRequest;
import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Models.Users.User;
import com.cni.elearning.Repositories.Users.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    
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
        var jwt = jwtService.generateToken(user,user.getRole(),user.getId());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }
}
