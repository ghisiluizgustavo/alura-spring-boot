package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.StatsTopic;
import br.com.alura.forum.models.Topic;

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
    private StatsTopic stats;
    private List<ResponseDTO> responses;

    public DetailsTopicsDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createdAt = topic.getCreatedAt();
        this.authorName = topic.getAuthor().getName();
        this.stats = topic.getStats();
        this.responses = new ArrayList<>();
        this.responses.addAll(topic.getResponses().stream().map(ResponseDTO::new).collect(Collectors.toList()));
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

    public StatsTopic getStats() {
        return stats;
    }

    public List<ResponseDTO> getResponses() {
        return responses;
    }
}
