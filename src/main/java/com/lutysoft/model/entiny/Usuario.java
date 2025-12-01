package com.lutysoft.model.entiny;

public class Usuario {

    private int id;
    private boolean active;
    private String nome;
    private String senha;
    private static Usuario usuarioLogado;

    public Usuario() {

    }

    public Usuario(int id, boolean active, String nome, String senha, Usuario usuarioLogado) {
        this.id = id;
        this.active = active;
        this.nome = nome;
        this.senha = senha;
        this.usuarioLogado = usuarioLogado;
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

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        Usuario.usuarioLogado = usuarioLogado;
    }
}
