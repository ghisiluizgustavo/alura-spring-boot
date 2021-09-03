package br.com.alura.forum.config.security;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repositorys.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository repository;

    public AuthenticationByTokenFilter(TokenService tokenService, UserRepository repository){
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);
        boolean valid = tokenService.isTokenValid(token);
        if (valid){
            authUser(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authUser(String token) {
        Long idUser = tokenService.getIdUser(token);
        Usuario user = repository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
         Optional<String> token = Optional.ofNullable(httpServletRequest.getHeader("Authorization"));
         if(token.isPresent()){
            String tokenStr = token.get();
            if(tokenStr.startsWith("Bearer ")){
                System.out.println(tokenStr);
                return tokenStr.substring(7, tokenStr.length());
            } else {
                return null;
            }
         } else {
             return null;
         }
    }
}
