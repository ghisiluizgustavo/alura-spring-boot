package br.com.alura.forum.repositorys;

import br.com.alura.forum.models.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void loadACourseWhenFindByName(){
        String nomeCurso = "HTML 5";

        Curso html = new Curso();
        html.setNome(nomeCurso);
        html.setCategoria("Programação");
        em.persist(html);

        Curso curso = courseRepository.findByNome(nomeCurso);
        Assert.assertNotNull(curso);
        Assert.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void dontLoadACourseWhenNameIsNotRegistered(){
        String nomeCurso = "Java completo";
        Curso curso = courseRepository.findByNome(nomeCurso);
        Assert.assertNull(curso);
    }
}