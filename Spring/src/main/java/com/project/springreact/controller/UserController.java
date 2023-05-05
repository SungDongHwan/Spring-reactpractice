package com.project.springreact.controller;

import com.project.springreact.dto.ResponseDTO;
import com.project.springreact.dto.UserDTO;
import com.project.springreact.model.User;
import com.project.springreact.security.TokenProvider;
import com.project.springreact.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/test")
    public ResponseEntity<?> tesetReact(@RequestBody UserDTO test){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String jsonBody = "{\"hello World!\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody,headers);
        String url = "http://10.125.121.207:3000";
        String response =restTemplate.postForObject(url, entity, String.class);
        return  ResponseEntity.ok(response);
    }
    @GetMapping("test1")
    public String test2(String aaa){
        return "hello World";
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO dto){
        try {
            if(dto == null || dto.getPassword() ==null){
                throw new RuntimeException("Invalid Password Value.");
            }
            User user = User.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
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
                dto.getPassword(),
                passwordEncoder
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
