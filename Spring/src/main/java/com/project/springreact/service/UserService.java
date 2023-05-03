package com.project.springreact.service;

import com.project.springreact.model.User;
import com.project.springreact.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User create(User userEntity){
        if(userEntity == null || userEntity.getUsername() == null){
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();
        if(userRepo.existsByUsername(username)){
            log.warn("Username already exists {}", username);
            throw new RuntimeException("Username already exists");
        }
        return userRepo.save(userEntity);
    }
    public User getByCredentials(final String username, final String password){
        return userRepo.findByUsernameAndPassword(username,password);
    }
}
