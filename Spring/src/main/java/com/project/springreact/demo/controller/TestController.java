package com.project.springreact.demo.controller;

import com.project.springreact.demo.dto.TestRequestBodyDTO;
import com.project.springreact.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/{id}")
    public String testController(@PathVariable(required = false) Integer id) {
        return "hello World ID " + id;
    }

    @GetMapping("/testRequestParam")// 만약 int id 를 쓴다면 null 값을 인정하지않으므로 에러.
    public String testControllerRequestParam(@RequestParam(required = false) Integer id) {
        return "hello Word! ID :" + id;
    }
    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! Id" +testRequestBodyDTO.getId()+"Message :" +testRequestBodyDTO.getMessage();
    }
    @GetMapping("/testResponseBody")
    public ResponseDTO <String> testControllerResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseDTO. And you got a 400 Problem!");
        // 이것은 String 을 type 지정해주는것이지 response가 String 이라는 뜻이아니야.
        // ResponseDTO 내부의 data<T>에서 T 를 지정해줬기 때문에 data(list) 가 List<String> 으로 출력될수 있는거지.
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }
    // 왜 wild card 가 쓰였는가. 무엇을 넣을지 몰라서 쓰인거 같긴한데.어차피 Entity에 들어갈 것은 정해진거 아닌가.
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

}

