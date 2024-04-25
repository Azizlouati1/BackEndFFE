package com.cni.elearning.Services;

import com.cni.elearning.Models.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {
    UserDetailsService userDetailsService();
    User getUserById(int id);
    List<User> getAllUsers(); 
    void deleteUser(int id);
    User updateUser(User user);
    User getUserByEmail(String email);
}
