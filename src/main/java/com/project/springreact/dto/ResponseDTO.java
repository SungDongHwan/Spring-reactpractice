package com.project.springreact.dto;

import com.project.springreact.persistence.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor // Type 을 자유롭게 하기 위해서
public class ResponseDTO <T>{
    private String error;
    private List<T> data;
    public static ResponseDTO<TodoDTO> convetDTO(List<Todo> entities){
        List<TodoDTO> dtos = new ArrayList<>();
        for (Todo todo : entities){
            TodoDTO todoDTO = new TodoDTO(todo);
            dtos.add(todoDTO);
        }
        return ResponseDTO.<TodoDTO>builder().data(dtos).build();

    }
}
