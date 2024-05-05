package com.cni.elearning.Services.Security;

import com.cni.elearning.Dtos.JwtAuthenticationResponse;
import com.cni.elearning.Dtos.SignInRequest;
import com.cni.elearning.Dtos.SignUpRequest;
import com.cni.elearning.Models.Users.User;

public interface IAuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);}
