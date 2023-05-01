package com.project.springreact.service;

import com.project.springreact.persistence.Todo;
import com.project.springreact.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository TodoRepo;

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