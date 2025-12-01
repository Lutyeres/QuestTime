package com.lutysoft.controller;

import com.lutysoft.model.dao.UsuarioDAO;
import com.lutysoft.model.entiny.Usuario;
import com.lutysoft.view.CadastroView;
import com.lutysoft.view.LoginView;
import com.lutysoft.view.TarefaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private final LoginView loginView;
    public static Usuario usuarioAtual = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    private boolean logar(){
        String usuario = loginView.getTxtUsuario().getText();
        String senha = loginView.getTxtSenha().getText();
        usuarioAtual = usuarioDAO.getUsuarioNameCadastrado(usuario);
        System.out.println(String.format("Id: %d, Ativo?: %b,  Usuario: %s, Senha: %s", usuarioAtual.getId(),usuarioAtual.isActive(),usuarioAtual.getNome(),usuarioAtual.getSenha()));
        if(usuarioAtual == null){
            return false;
        }else{
            if(usuarioAtual.isActive()){
                if(usuarioAtual.getNome().equals(usuario) && usuarioAtual.getSenha().equals(senha)){
                   usuarioAtual.setUsuarioLogado(usuarioAtual);
                   return true;
                }else{
                    return false;
                }
            }else {
                return false;
            }


        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand();

        switch (acao){
            case "Entrar":
                if(logar()){
                    loginView.fechar();
                    TarefaView tarefaView = new TarefaView();
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario ou senha incorretos!");
                }
            break;
            case "Cadastrar":
                loginView.fechar();
                CadastroView cadastroView = new CadastroView();
            break;
        }
    }
}
