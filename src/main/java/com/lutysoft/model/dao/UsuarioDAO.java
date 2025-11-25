package com.lutysoft.model.dao;

import com.lutysoft.util.connection.ConnectionFactory;
import com.lutysoft.util.connection.MySqlConnectionFactory;

public class UsuarioDAO {

    private final ConnectionFactory conexão;

    public UsuarioDAO(){
        conexão = new MySqlConnectionFactory();
    }

    private static final String SQL_INSERT_USUARIO = "INSERT INTO usuario(usuarioName, usuarioPassword) values( ?, ? ) ";
    private static final String SQL_UPDATE_SENHA = "UPDATE usuario SET usuarioPassword = ? WHERE idUsuario = ?";
    private static final String SQL_DELETE_USUARIO = "DELETE FROM usuario WHERE idUsuario = ?";

}
