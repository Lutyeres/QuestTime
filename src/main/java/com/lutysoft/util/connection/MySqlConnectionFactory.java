package com.lutysoft.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory implements ConnectionFactory {

    private final String usuario = "root";
    private final String senha = "";
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
            System.out.println("Houve um erro no SQL:sqlexception sql-" + sql);
            return null;
        } catch (ClassNotFoundException cnf) {
            System.out.println("Houve um erro no DRIVER: classnotfoundexception-" + cnf);
            return null;
        }
    }
}
