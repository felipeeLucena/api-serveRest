package model;

public class UserRequest {

    public String nome;
    public String email;
    public String password;
    public String administrador;

    public UserRequest(String nome, String email, String password, String administrador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = administrador;
    }
}
