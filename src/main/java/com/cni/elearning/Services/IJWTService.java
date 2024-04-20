package com.cni.elearning.Services;

import javax.crypto.SecretKey;

import com.cni.elearning.Models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface    IJWTService {

    String generateToken(UserDetails userdetails);

    String extractUsername(String token);
    
    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userdetails);
}
