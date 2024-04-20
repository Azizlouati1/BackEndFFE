package com.cni.elearning.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Role;
import com.cni.elearning.Models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
