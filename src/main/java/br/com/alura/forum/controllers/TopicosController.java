package br.com.alura.forum.controllers;

import br.com.alura.forum.controllers.dto.DetalhesTopicosDTO;
import br.com.alura.forum.controllers.dto.TopicoDTO;
import br.com.alura.forum.controllers.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controllers.form.TopicoForm;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositorys.CursoRepository;
import br.com.alura.forum.repositorys.TopicoRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Retorna um Pageable
     * @RequestParam é parametros de URL
     * O objeto Pageable recebido é {page=0&size=10&sort=id,asc} na URL
     */
    @GetMapping()
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
                                @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao){

        Page<Topico> topicos;
        if(nomeCurso == null){
            topicos = topicoRepository.findAll(paginacao);
        } else {
            topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
        }
        return TopicoDTO.converter(topicos);
    }

    @PostMapping()
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicosDTO> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(value -> ResponseEntity.ok(new DetalhesTopicosDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualiazar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico));
    }
}
