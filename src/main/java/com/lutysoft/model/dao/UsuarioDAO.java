package com.lutysoft.model.dao;

import com.lutysoft.model.entiny.Usuario;
import com.lutysoft.util.connection.ConnectionFactory;
import com.lutysoft.util.connection.MySqlConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private final ConnectionFactory conexão;

    public UsuarioDAO(){
        conexão = new MySqlConnectionFactory();
    }

    private static final String SQL_INSERT_USUARIO = "INSERT INTO usuario(usuarioName, usuarioPassword) values( ?, ? ) ";
    private static final String SQL_UPDATE_SENHA = "UPDATE usuario SET usuarioPassword = ? WHERE idUsuario = ?";
    private static final String SQL_DELETE_USUARIO = "DELETE FROM usuario WHERE idUsuario = ?";
    private static final String SQL_SELECT_IDUSUARIO = "SELECT * FROM usuario WHERE idUsuario = ?";
    private static final String SQL_SELECT_USUARIONAME = "SELECT * FROM usuario WHERE usuarioName = ?";


    public String create (Usuario usuario){
        return usuario.getId() == 0 ? add(usuario) : update(usuario);
    }

    private String add(Usuario usuario){
        Usuario usuarioTest = getUsuarioCadastrado(usuario.getNome());
        if(usuarioTest != null){
            return String.format("0");
        }

        try(Connection con = conexão.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_INSERT_USUARIO)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2,usuario.getSenha());

            int resultado = stmt.executeUpdate();
            return resultado == 1 ? "Usuário Cadastrado" : "Usuário Não Cadastrado";
        } catch (SQLException e) {
            return String.format("ERROR SQL: 0");
        }
    }

    private String update(Usuario usuario){

        try(Connection con = conexão.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_SENHA)){
            stmt.setString(1, usuario.getSenha());
            stmt.setInt(2, usuario.getId());

            int resultado = stmt.executeUpdate();
            return resultado == 1 ? "Senha Alterada Com Sucesso" : "Não foi possível alterar a senha";
        }catch (SQLException e){
            return String.format("ERROR SQL : %s", e.getMessage());
        }
    }

    private Usuario getUsuarioCadastrado(String usuarioName){

        try(Connection con = conexão.getConnection(); PreparedStatement stmt = con.prepareStatement(SQL_SELECT_USUARIONAME)){
            stmt.setString(1, usuarioName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return getUsuario(rs);
            }

        } catch (SQLException sql) {
            System.out.println(String.format("Error: %s", sql.getMessage()));

        }

        return null;
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();

        usuario.setId(rs.getInt("idUsuario"));
        usuario.setNome(rs.getString("usuarioName"));
        usuario.setSenha("usuarioPassword");

        return usuario;
    }

}
