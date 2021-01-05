package br.com.alura.forum.repositorys;

import br.com.alura.forum.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCurso_Nome(String nomeCurso, Pageable paginacao);
}
