package com.project.springreact.service;

import com.project.springreact.persistence.Todo;
import com.project.springreact.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {
    @Autowired
    private TodoRepository TodoRepo;
    public List<Todo> create(Todo entity){
        validate(entity);
        TodoRepo.save(entity);
        log.info("Entity ID : {} is saved." , entity.getId() );
        return TodoRepo.findByUserID(entity.getUserId());

    }
    public List<Todo> update(Todo entity){
        validate(entity);
        Optional<Todo> origin = TodoRepo.findById(entity.getId());
        if(origin.isPresent()){
            Todo todo = origin.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.getDone());
            TodoRepo.save(todo);
        }
        log.info("Entity ID : {} is Updated to {}." , entity.getId(),entity.getTitle());
        return retrieve(entity.getUserId());
    }
    public List<Todo> retrieve(String userId) {
        log.info("Entity UserID: {}  ",TodoRepo.findByUserID(userId));
        return TodoRepo.findByUserID(userId);
    }
    public List<Todo> delete(Todo entity){
        validate(entity);
        try{
            log.info("Entity UserID: {} is deleted", TodoRepo.findByUserID(entity.getUserId()));
            TodoRepo.delete(entity);
        }catch (Exception e){
            log.error("error deleting entity", entity.getId(),e);
            throw  new RuntimeException("error deleting entity"+ entity.getId());
        }
        return retrieve(entity.getUserId());
    }
    private static void validate(Todo entity) {
        // validation
        if(entity == null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity can not be null.");
        }
        if(entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public String testService(){
        // Todoentity
        Todo entity = Todo.builder().title("My First todo item")
                                    .build();
        // 저장
        TodoRepo.save(entity);
        Todo savedEntity = TodoRepo.findById(entity.getId()).get();
        return savedEntity.getTitle();

    }
}