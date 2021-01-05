package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.StatusTopico;
import br.com.alura.forum.models.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsTopicsDTO {

    private Long id;
    private String message;
    private String title;
    private LocalDateTime createdAt;
    private String authorName;
    private StatusTopico stats;
    private List<ResponseDTO> responses;

    public DetailsTopicsDTO(Topico topico) {
        this.id = topico.getId();
        this.title = topico.getTitulo();
        this.message = topico.getMensagem();
        this.createdAt = topico.getDataCriacao();
        this.authorName = topico.getAutor().getNome();
        this.stats = topico.getStatus();
        this.responses = new ArrayList<>();
        this.responses.addAll(topico.getResponses().stream().map(ResponseDTO::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAuthorName() {
        return authorName;
    }

    public StatusTopico getStats() {
        return stats;
    }

    public List<ResponseDTO> getResponses() {
        return responses;
    }
}
