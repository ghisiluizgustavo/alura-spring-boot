package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Response;

import java.time.LocalDateTime;

public class ResponseDTO {

    private final Long id;
    private final String message;
    private final LocalDateTime createdAt;
    private final String authorName;

    public ResponseDTO(Response response){
        this.id = response.getId();
        this.message = response.getMessage();
        this.createdAt = response.getCreatedAt();
        this.authorName = response.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAuthorName() {
        return authorName;
    }
}
