package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class TopicDTO {

    private Long id;
    private String message;
    private String title;
    private LocalDateTime createdAt;

    public TopicDTO(Topic topic){
        this.id = topic.getId();
        this.message = topic.getMessage();
        this.title = topic.getTitle();
        this.createdAt = topic.getCreatedAt();
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

    public static Page<TopicDTO> converter(Page<Topic> topics){
        return topics.map(TopicDTO::new);
    }
}
