package com.project.springreact.dto;

import com.project.springreact.persistence.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// DB에 들어가는 user Id 가 없는이유 : 스프링 시큐리티를 통해 인증구현을 하므로
// 가능한한 숨길수 있으면 숨길것.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String Id;
    private String title;
    private Boolean done;
    public TodoDTO(final Todo todo){
        this.Id = todo.getId();
        this.title = todo.getTitle();
        this.done = todo.getDone();

    }
}
