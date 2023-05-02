package com.project.springreact.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation 참고
    // * 대신 t 로 todo 를 받아왔다는것 생각하기.     @Query("select * from Todo  where t.userId = ?1")는 작동하지않음
    @Query("select t from Todo t where t.userId = ?1")
    List<Todo> findByUserID(String userID);


}
