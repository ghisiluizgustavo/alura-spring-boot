package br.com.alura.forum.controllers.dto;

public class TokenDTO {

    private String token;
    private String bearer;

    public TokenDTO(String token, String bearer) {
        this.token = token;
        this.bearer = bearer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
