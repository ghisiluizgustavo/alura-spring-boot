package br.com.alura.forum.config.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
         Optional<String> token = Optional.ofNullable(httpServletRequest.getHeader("Authorization"));
         if(token.isPresent()){
            String tokenStr = token.get();
            if(tokenStr.startsWith("Bearer")){
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
