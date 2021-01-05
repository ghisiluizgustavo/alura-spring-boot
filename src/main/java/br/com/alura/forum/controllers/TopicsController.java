package br.com.alura.forum.controllers;

import br.com.alura.forum.controllers.dto.DetailsTopicsDTO;
import br.com.alura.forum.controllers.dto.TopicDTO;
import br.com.alura.forum.controllers.form.ActualizationTopicForm;
import br.com.alura.forum.controllers.form.TopicForm;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositorys.CourseRepository;
import br.com.alura.forum.repositorys.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Retorna um Pageable
     * @RequestParam é parametros de URL
     * O objeto Pageable recebido é {page=0&size=10&sort=id,asc} na URL
     *
     * @Cacheable informa ao Spring que é para guardar em cache o resultado da requisição
     * Para observar o funcinamento do cache é possível olhar o console (onde está rodando o Spring)
     * pois nele ira mostrar os Select's que o Spring usou para ir no banco
     *
     * Ele faz um select a primeira vez q o método é acionado, na segunda ele ja usa o cache
     */
    @GetMapping()
    @Cacheable(value = "topicList")
    public Page<TopicDTO> list(@RequestParam(required = false) String courseName,
                                @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        Page<Topico> topics;
        if(courseName == null){
            topics = topicRepository.findAll(pageable);
        } else {
            topics = topicRepository.findByCurso_Nome(courseName, pageable);
        }
        return TopicDTO.converter(topics);
    }

    /**
     * @CacheEvict informa ao Spring que quando esse método for chamado ele deve limpar o cache
     */
    @PostMapping()
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDTO> post(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder){
        Topico topico = topicForm.convert(courseRepository);
        topicRepository.save(topico);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsTopicsDTO> detail(@PathVariable Long id){
        Optional<Topico> topic = topicRepository.findById(id);
        return topic.map(value -> ResponseEntity.ok(new DetailsTopicsDTO(value))).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Topico> topic = topicRepository.findById(id);
        if (topic.isPresent()){
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid ActualizationTopicForm form){
        Topico topico = form.update(id, topicRepository);
        return ResponseEntity.ok(new TopicDTO(topico));
    }
}
