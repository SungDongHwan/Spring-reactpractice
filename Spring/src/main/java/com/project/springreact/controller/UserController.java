package com.project.springreact.controller;

import com.project.springreact.dto.ResponseDTO;
import com.project.springreact.dto.UserDTO;
import com.project.springreact.model.User;
import com.project.springreact.security.TokenProvider;
import com.project.springreact.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO dto){
        try {
            if(dto == null || dto.getPassword() ==null){
                throw new RuntimeException("Invalid Password Value.");
            }
            User user = User.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .build();
            User registerdUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .id(registerdUser.getId())
                    .username(registerdUser.getUsername())
                    .build();
            return  ResponseEntity.ok(responseUserDTO);
        }catch (Exception e){
            //유저 정보는 항상 하나이므로 user DTO 리턴.
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signin")
    public  ResponseEntity <?> authenticate(@RequestBody UserDTO dto){
        User user = userService.getByCredentials(
                dto.getUsername(),
                dto.getPassword()
        );
        if(user != null){
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .username(user.getUsername())
                    .id(user.getId())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }else{
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed.")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
