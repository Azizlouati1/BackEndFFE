package com.cni.elearning.Services.Security;

import com.cni.elearning.Models.Users.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface    IJWTService {

    String generateToken(UserDetails userdetails , Role role);

    String extractUsername(String token);
    
    boolean isTokenValid(String token, UserDetails userDetails);

}
