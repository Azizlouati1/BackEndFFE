package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Users.User;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cni.elearning.Repositories.Users.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public User updateUser(User user, int id) {
        User userToSave = userRepository.findById(id).orElse(null);
        assert userToSave != null;
        if (user.getId() == userToSave.getId()) {
            user.setRole(userToSave.getRole());
                if (!Objects.equals(user.getPassword(), userToSave.getPassword())){user.setPassword(passwordEncoder.encode(user.getPassword()));}
            return userRepository.save(user);
        }
        throw new RuntimeException("User Id NOT MATCH");
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
