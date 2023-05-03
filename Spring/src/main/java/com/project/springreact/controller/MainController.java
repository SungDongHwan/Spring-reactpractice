package com.project.springreact.controller;

import com.project.springreact.dto.ResponseDTO;
import com.project.springreact.dto.TodoDTO;
import com.project.springreact.model.Todo;
import com.project.springreact.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class MainController {
    @Autowired
    TodoService service;

    @PostMapping("/test")
    public ResponseEntity<?> testTodoResponseEntity(@RequestBody TodoDTO dto){
        try {
            String temporaryUserId = "성동환";            // Todo 라는 객체를 넣어가지고
            Todo entity = TodoDTO.todoEntity(dto);
            entity.setUserId(temporaryUserId);
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
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "성동환"; // temporary user id.
        List<Todo>  entities = service.retrieve(temporaryUserId);
        ResponseDTO<TodoDTO> response = ResponseDTO.convertDTO(entities);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodoList(@RequestBody TodoDTO dto){
        String temporaryUserid = "성동환";
        Todo entity = TodoDTO.todoEntity(dto);
        entity.setUserId(temporaryUserid);
        List<Todo>  entities = service.update(entity);
        ResponseDTO<TodoDTO> response = ResponseDTO.convertDTO(entities);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteTodoList(@RequestBody TodoDTO dto){
        try {
            String temporaryUserid = "성동환";
            Todo entity = TodoDTO.todoEntity(dto);
            entity.setUserId(temporaryUserid);
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
