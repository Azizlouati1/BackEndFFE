package com.cni.elearning.Services;

import com.cni.elearning.Dtos.JwtAuthenticationResponse;
import com.cni.elearning.Dtos.SignInRequest;
import com.cni.elearning.Dtos.SignUpRequest;
import com.cni.elearning.Models.User;

public interface IAuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);}
