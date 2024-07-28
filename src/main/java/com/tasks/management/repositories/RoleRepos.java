package com.tasks.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.tasks.management.models.ERole;
import com.tasks.management.models.Role;

public interface RoleRepos extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
