package com.lutysoft.app;

import com.lutysoft.model.dao.TarefaDao;
import com.lutysoft.model.entiny.Tarefa;
import com.lutysoft.util.connection.ConnectionFactory;
import com.lutysoft.util.connection.MySqlConnectionFactory;
import com.lutysoft.view.TarefaView;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
         /*
        Tarefa tarefa1 = new Tarefa(LocalDateTime.now(),null,0,"estudo","estudo de programação");
        TarefaDao tarefaSalvar = new TarefaDao();
        System.out.println(tarefaSalvar.create(tarefa1));



        LocalDateTime horaAgora = null;
        */
        /*
        TarefaDao tarefaDao = new TarefaDao();
        Tarefa tarefaCash = new Tarefa();
        List<Tarefa> listaTarefa = new ArrayList<>();
        listaTarefa = tarefaDao.getAllTarefas();

        for(int i = 0; i < listaTarefa.size(); i++){
            tarefaCash = listaTarefa.get(i);
            //String dataInicioFormatada = tarefaCash.getDataHorarioInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
            if(tarefaCash.getDataHorarioFinal() != null) {
                Duration duracao = Duration.between(tarefaCash.getDataHorarioInicio(), tarefaCash.getDataHorarioFinal());

                long horas = duracao.toHours();             // Total de horas completas
                long minutos = duracao.toMinutes() % 60;    // Minutos restantes após as horas
                long segundos = duracao.getSeconds() % 60;  // Segundos restantes após os minutos

                String tempoDecorrido = String.format("%d horas, %d minutos e %d segundos", horas, minutos, segundos);
                String dataInicioFormatada = tarefaCash.getDataHorarioInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
                String dataFinalFormatada = tarefaCash.getDataHorarioFinal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
                System.out.println(String.format("\n Id: %s \n Tarefa: %s \n Descrição: %s \n Inicio: %s \n Final: %s \n Tempo Gasto: %s \n", tarefaCash.getId(), tarefaCash.getNome(), tarefaCash.getObs(), dataInicioFormatada, dataFinalFormatada, tempoDecorrido));

            }else{
                Duration duracao = Duration.between(tarefaCash.getDataHorarioInicio(), LocalDateTime.now());

                long horas = duracao.toHours();             // Total de horas completas
                long minutos = duracao.toMinutes() % 60;    // Minutos restantes após as horas
                long segundos = duracao.getSeconds() % 60;  // Segundos restantes após os minutos

                String tempoDecorrido = String.format("%d horas, %d minutos e %d segundos", horas, minutos, segundos);

                String dataInicioFormatada = tarefaCash.getDataHorarioInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
                String dataFinalFormatada = "";
                System.out.println(String.format("\n Id: %s \n Tarefa: %s \n Descrição: %s \n Inicio: %s \n Final: %s \n Tempo Gasto: Tarefa não finalizada - Tempo até o momento: %s \n", tarefaCash.getId(), tarefaCash.getNome(), tarefaCash.getObs(), dataInicioFormatada, dataFinalFormatada, tempoDecorrido));

            }
        }
        */


        TarefaView tarefaView = new TarefaView();








    }
}
