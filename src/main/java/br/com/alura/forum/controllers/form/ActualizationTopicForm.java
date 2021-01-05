package br.com.alura.forum.controllers.form;

import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repositorys.TopicRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ActualizationTopicForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;
    @NotEmpty
    @NotNull
    @Length(min = 10)
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getOne(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);
        return topic;

    }
}
