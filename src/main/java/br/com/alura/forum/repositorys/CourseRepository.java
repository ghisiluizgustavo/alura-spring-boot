package br.com.alura.forum.repositorys;

import br.com.alura.forum.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nomeCurso);
}
