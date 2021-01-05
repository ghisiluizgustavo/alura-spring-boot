package br.com.alura.forum.controllers;

import br.com.alura.forum.controllers.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm loginForm){
        return ResponseEntity.ok().body(loginForm);
    }
}