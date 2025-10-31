package com.lutysoft.app;

import com.lutysoft.util.connection.MySqlConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection conexão;

        //System.out.println("Hello World!");
         try{
             conexão = new MySqlConnectionFactory().getConnection();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }
}
