package com.lutysoft.controller;

import com.lutysoft.model.dao.TarefaDao;
import com.lutysoft.model.entiny.Tarefa;
import com.lutysoft.view.RelatorioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RelatorioController implements ActionListener {

    private final RelatorioView relatorioView;


    public RelatorioController(RelatorioView relatorioView){
        this.relatorioView = relatorioView;
        relatoriosTabela();

    }

    private void relatoriosTabela(){
        TarefaDao tarefaDao = new TarefaDao();
        List<Tarefa> listaTarefa = new ArrayList<>();
        listaTarefa = tarefaDao.getAllTarefas();
        relatorioView.getTableModel().setRowCount(0);

        for(int i = 0; i < listaTarefa.size(); i++){
            Tarefa tarefaTemp = listaTarefa.get(i);
            if(tarefaTemp.getDataHorarioFinal() != null) {
                Duration duracao = Duration.between(tarefaTemp.getDataHorarioInicio(), tarefaTemp.getDataHorarioFinal());

                long horas = duracao.toHours();             // Total de horas completas
                long minutos = duracao.toMinutes() % 60;    // Minutos restantes após as horas
                long segundos = duracao.getSeconds() % 60;  // Segundos restantes após os minutos
                String tempoDecorrido;
                if(horas != 0L){
                    tempoDecorrido = String.format("%d horas, %d minutos e %d segundos", horas, minutos, segundos);
                }else if(minutos != 0){
                    tempoDecorrido = String.format("%d minutos e %d segundos",minutos, segundos);
                }else{
                    tempoDecorrido = String.format("%d segundos", segundos);
                }

                String dataInicioFormatada = tarefaTemp.getDataHorarioInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
                String dataFinalFormatada = tarefaTemp.getDataHorarioFinal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
                Object[] linhaDeDados = new Object[]{
                        tarefaTemp.getId(),
                        tarefaTemp.getNome(),
                        tarefaTemp.getObs(),
                        dataInicioFormatada,
                        dataFinalFormatada,
                        tempoDecorrido
                };
                relatorioView.getTableModel().addRow(linhaDeDados);
            }else{
                Duration duracao = Duration.between(tarefaTemp.getDataHorarioInicio(), LocalDateTime.now());

                long horas = duracao.toHours();             // Total de horas completas
                long minutos = duracao.toMinutes() % 60;    // Minutos restantes após as horas
                long segundos = duracao.getSeconds() % 60;  // Segundos restantes após os minutos
                String tempoDecorrido;
                if(horas != 0L){
                    tempoDecorrido = String.format("%d horas, %d minutos e %d segundos", horas, minutos, segundos);
                }else if(minutos != 0){
                    tempoDecorrido = String.format("%d minutos e %d segundos",minutos, segundos);
                }else{
                    tempoDecorrido = String.format("%d segundos", segundos);
                }

                String dataInicioFormatada = tarefaTemp.getDataHorarioInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss"));
                String dataFinalFormatada = "";
                Object[] linhaDeDados = new Object[]{
                        tarefaTemp.getId(),
                        tarefaTemp.getNome(),
                        tarefaTemp.getObs(),
                        dataInicioFormatada,
                        dataFinalFormatada,
                        tempoDecorrido
                };
                relatorioView.getTableModel().addRow(linhaDeDados);

            }








        }



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String acao = e.getActionCommand();
        System.out.println(acao);
        System.out.println(source);

        switch (acao){
            case "Atualizar Relatório":
                relatoriosTabela();
            break;
        }
    }
}
