package com.lutysoft.app;

import com.lutysoft.model.dao.TarefaDao;
import com.lutysoft.model.entiny.Tarefa;
import com.lutysoft.util.connection.ConnectionFactory;
import com.lutysoft.util.connection.MySqlConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Tarefa tarefa1 = new Tarefa(LocalDateTime.now(),null,0,"estudo","estudo de programação");
        TarefaDao tarefaSalvar = new TarefaDao();
        System.out.println(tarefaSalvar.create(tarefa1));






    }
}
