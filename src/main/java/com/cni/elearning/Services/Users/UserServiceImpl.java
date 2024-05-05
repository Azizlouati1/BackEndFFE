package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Users.User;

import java.util.List;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.cni.elearning.Repositories.Users.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
