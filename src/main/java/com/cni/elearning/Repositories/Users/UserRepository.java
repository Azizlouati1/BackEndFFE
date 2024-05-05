package com.cni.elearning.Repositories.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Models.Users.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
