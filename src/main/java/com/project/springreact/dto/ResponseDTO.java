package com.project.springreact.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor // Type 을 자유롭게 하기 위해서
public class ResponseDTO <T>{
    private String error;
    private List<T> data;
}
