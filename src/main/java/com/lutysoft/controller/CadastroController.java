package com.lutysoft.controller;

import com.lutysoft.model.dao.UsuarioDAO;
import com.lutysoft.model.entiny.Usuario;
import com.lutysoft.view.CadastroView;
import com.lutysoft.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroController implements ActionListener {
    private final CadastroView cadastroView;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuarioTemp = new Usuario();

    public CadastroController(CadastroView cadastroView) {
        this.cadastroView = cadastroView;
    }

    private boolean add(){
        String nome = cadastroView.getTxtNome().getText();
        String senha = cadastroView.getTxtSenha().getText();
        String senhaConf = cadastroView.getTxtConfirmaSenha().getText();
        usuarioTemp = usuarioDAO.getUsuarioNameCadastrado(nome);

        if(usuarioTemp == null) {
            if (senha.equals(senhaConf)) {
                Usuario usuario = new Usuario(0, true, nome, senha, null);
                usuarioDAO.create(usuario);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "As senhas não podem ser diferentes!");
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuario já cadastrado!");
            return false;

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand();

        switch (acao){
            case "Cadastrar":
                if(add()){
                    JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
                    cadastroView.fechar();
                    LoginView loginView = new LoginView();
                }
            break;
            case "Cancelar":
                cadastroView.fechar();
                LoginView loginView = new LoginView();
            break;
        }
    }
}
