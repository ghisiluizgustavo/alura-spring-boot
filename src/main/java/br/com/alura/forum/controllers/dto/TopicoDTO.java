package br.com.alura.forum.controllers.dto;

import br.com.alura.forum.models.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {

    private Long id;
    private String mensagem;
    private String titulo;
    private LocalDateTime dataCriacao;

    public TopicoDTO(Topico topico){
        this.id = topico.getId();
        this.mensagem = topico.getMensagem();
        this.titulo = topico.getTitulo();
        this.dataCriacao = topico.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public static List<TopicoDTO> converter(List<Topico> topicos){
        return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }
}
