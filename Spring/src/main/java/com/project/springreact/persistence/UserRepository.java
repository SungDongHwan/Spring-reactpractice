package com.project.springreact.persistence;

import com.project.springreact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
