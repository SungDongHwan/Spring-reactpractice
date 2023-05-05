package com.project.springreact.controller;

import com.project.springreact.dto.ResponseDTO;
import com.project.springreact.dto.TodoDTO;
import com.project.springreact.model.Todo;
import com.project.springreact.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class MainController {
    @Autowired
    TodoService service;

    @PostMapping("/test")
    public ResponseEntity<?> testTodoResponseEntity(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto){
        try {
            Todo entity = TodoDTO.todoEntity(dto);
            entity.setId(null);
            entity.setUserId(userId);
             // DTO 에는 id 값이 없으므로 초기화 필요함
            // create service 를 통한 DB에 create 해서 넣기.
            List<Todo> entities = service.create(entity);
           // List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).toList();
            // 이걸 잘 이해 못하겠어. dto 들을 다시 바꿀 필요가 있는건가? dto 를 그냥넣으면 되는거 아닌가? 왜 여기서 이런 코드를?
            ResponseDTO<TodoDTO> response = ResponseDTO.convertDTO(entities);
            return  ResponseEntity.ok().body(response);
            // 이건 ResponseDTO 로 패키징 하는과정이다.
            }catch (Exception   e){
             ResponseDTO<String> response = ResponseDTO.<String>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId){
        List<Todo>  entities = service.retrieve(userId);
        ResponseDTO<TodoDTO> response = ResponseDTO.convertDTO(entities);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodoList(@AuthenticationPrincipal String userId,@RequestBody TodoDTO dto){
        Todo entity = TodoDTO.todoEntity(dto);
        entity.setUserId(userId);
        List<Todo>  entities = service.update(entity);
        ResponseDTO<TodoDTO> response = ResponseDTO.convertDTO(entities);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteTodoList(@AuthenticationPrincipal String userId,@RequestBody TodoDTO dto){
        try {
            Todo entity = TodoDTO.todoEntity(dto);
            entity.setUserId(userId);
            List<Todo> entities = service.delete(entity);
            ResponseDTO<TodoDTO> response = ResponseDTO.convertDTO(entities);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


}
