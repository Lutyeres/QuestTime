package com.lutysoft.model.entiny;

public class Usuario {

    private int id;
    private boolean active;
    private String nome;
    private String senha;

    public Usuario() {

    }

    public Usuario(int id, boolean active, String nome, String senha) {
        this.id = id;
        this.active = active;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
