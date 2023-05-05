package com.project.springreact.service;

import com.project.springreact.model.User;
import com.project.springreact.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public User getByCredentials( String username, String password, PasswordEncoder encoder){
        final User originalUser = userRepo.findByUsername(username);
        // match 를 통해서 확인 - Bcrypt PAssword Encoder 는 같은 값을 코딩해도 더미를 붙혀 주므로 Salting 된것을
        // 비교하기위해서는 match 로 비교하는것이 맞다.
        if(originalUser != null && encoder.matches(password,originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }
}
