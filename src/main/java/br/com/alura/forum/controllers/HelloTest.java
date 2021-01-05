package br.com.alura.forum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTest {

    /**
     * Apenas para testar a autenticação pelo navegador
     */
    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }
}
