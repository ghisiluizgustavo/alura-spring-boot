package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Resposta;

import java.time.LocalDateTime;

public class ResponseDTO {

    private final Long id;
    private final String message;
    private final LocalDateTime createdAt;
    private final String authorName;

    public ResponseDTO(Resposta resposta){
        this.id = resposta.getId();
        this.message = resposta.getMensagem();
        this.createdAt = resposta.getDataCriacao();
        this.authorName = resposta.getAutor().getNome();
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
