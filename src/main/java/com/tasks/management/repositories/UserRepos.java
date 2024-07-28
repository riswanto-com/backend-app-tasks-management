package com.tasks.management.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasks.management.models.User;
import java.util.List;


public interface UserRepos extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User>findByUsernameContains(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
