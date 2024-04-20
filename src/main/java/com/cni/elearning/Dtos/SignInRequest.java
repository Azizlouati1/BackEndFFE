package com.cni.elearning.Dtos;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
