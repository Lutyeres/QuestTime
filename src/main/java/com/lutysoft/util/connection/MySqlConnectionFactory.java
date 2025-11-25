package com.lutysoft.util.connection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory implements ConnectionFactory {

    private final String usuario = "redehome";
    private final String senha = "Usuario24savyo!";
    private final String url = "jdbc:mysql://localhost:3306/questtime?serverTimezone=UTC";
    private Connection connection;


    @Override
    public Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem sucedida!");
            return connection;
        }catch (SQLException sql){
            JOptionPane.showMessageDialog(null, "Houve um erro no SQL: sqlexception sql\n"+sql);
            //System.out.println("Houve um erro no SQL:sqlexception sql-" + sql);
            return null;
        } catch (ClassNotFoundException cnf) {
            JOptionPane.showMessageDialog(null, "Houve um erro no DRIVER: classnotfoundexception\n"+cnf);
            //System.out.println("Houve um erro no DRIVER: classnotfoundexception-" + cnf);
            return null;
        }
    }
}
