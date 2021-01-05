package br.com.alura.forum.repositorys;

import br.com.alura.forum.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByNome(String nomeCurso);
}
