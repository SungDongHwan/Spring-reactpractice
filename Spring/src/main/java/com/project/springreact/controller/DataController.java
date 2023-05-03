package com.project.springreact.controller;

import com.project.springreact.dto.DataDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
public class DataController {
    @PostMapping  ("/congestion")
    public ResponseEntity<?> testPythonResponse(@RequestBody DataDTO dto){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String jsonBody = "{\"new_value\": 1400, \"new_termCode\": \"BNCT\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody,headers);
        String url = "http://10.125.121.220:5001/api/congestion";
        String response =restTemplate.postForObject(url, entity, String.class);
        return ResponseEntity.ok(response);
    }
}
