package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class TopicDTO {

    private Long id;
    private String message;
    private String title;
    private LocalDateTime createdAt;

    public TopicDTO(Topico topico){
        this.id = topico.getId();
        this.message = topico.getMensagem();
        this.title = topico.getTitulo();
        this.createdAt = topico.getDataCriacao();
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

    public static Page<TopicDTO> converter(Page<Topico> topics){
        return topics.map(TopicDTO::new);
    }
}
