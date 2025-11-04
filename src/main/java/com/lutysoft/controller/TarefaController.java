package com.lutysoft.controller;

import com.lutysoft.model.dao.TarefaDao;
import com.lutysoft.model.entiny.Tarefa;
import com.lutysoft.view.TarefaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class TarefaController implements ActionListener {

    private final TarefaView tarefaView;
    private Tarefa tarefaTemp = new Tarefa();
    private LocalDateTime horaAgora;

    public TarefaController(TarefaView tarefaView) {
        this.tarefaView = tarefaView;
    }

    private void layoutNovaTarefa(){
        tarefaView.getTxtIdTarefa().setText(null);
        tarefaView.getTxtIdTarefa().setEnabled(false);
        tarefaView.getTxtIdTarefa().setFocusable(false);

        tarefaView.getTxtNome().setEnabled(true);
        tarefaView.getTxtNome().setEditable(true);
        tarefaView.getTxtNome().setFocusable(true);
        tarefaView.getTxtNome().setText(null);

        tarefaView.getTxtDescricao().setEnabled(true);
        tarefaView.getTxtDescricao().setEditable(true);
        tarefaView.getTxtDescricao().setFocusable(true);
        tarefaView.getTxtDescricao().setText(null);

        tarefaView.getLblStatus().setVisible(false);

        tarefaView.getBtnIniciar().setEnabled(true);

        tarefaView.getBtnNova().setEnabled(false);
        tarefaView.getBtnOk().setVisible(true);
        tarefaView.getBtnCancelar().setVisible(true);
        tarefaView.getBtnAlterar().setVisible(false);
        tarefaView.getBtnExcluir().setVisible(false);

        tarefaTemp.limparTarefa();
    }

    private void cancelar(){
        tarefaView.layoutPadrao();
    }

    private void layoutBuscarPeloId(){
        //Botao Nova Tarefa
        tarefaView.getBtnNova().setEnabled(true);
        tarefaView.getBtnNova().setVisible(true);
        //Botao Alterar
        tarefaView.getBtnAlterar().setEnabled(true);
        tarefaView.getBtnAlterar().setVisible(true);
        //Botao Excluir
        tarefaView.getBtnExcluir().setEnabled(true);
        tarefaView.getBtnExcluir().setVisible(true);
        //Desvisualizar os botoes Ok e Cancelar
        tarefaView.getBtnCancelar().setVisible(false);
        tarefaView.getBtnOk().setVisible(false);
        //Desativar os botoes Iniciar e Finalizar Tarefa
        tarefaView.getBtnIniciar().setEnabled(false);
        tarefaView.getBtnFinalizar().setEnabled(false);
        //Tonar o painel de acompanhamento visivel
        tarefaView.getLblStatus().setVisible(true);
        //TextField do Nome
        tarefaView.getTxtNome().setEnabled(true);
        tarefaView.getTxtNome().setFocusable(false);
        tarefaView.getTxtNome().setEditable(false);
        //TextField da Descrição
        tarefaView.getTxtDescricao().setEnabled(true);
        tarefaView.getTxtDescricao().setFocusable(false);
        tarefaView.getTxtDescricao().setEditable(false);
        //TextField do ID
        tarefaView.getTxtIdTarefa().setEnabled(true);
        tarefaView.getTxtIdTarefa().setFocusable(true);
        tarefaView.getTxtIdTarefa().setEditable(true);
        //buscarPeloId();

    }

    private void layoutAlterar(){
        tarefaView.getBtnNova().setVisible(false);
        tarefaView.getBtnExcluir().setVisible(false);
        tarefaView.getBtnAlterar().setEnabled(false);
        tarefaView.getBtnAlterar().setVisible(true);
        tarefaView.getBtnOk().setVisible(true);
        tarefaView.getBtnCancelar().setVisible(true);
        tarefaView.getTxtIdTarefa().setEnabled(false);
        tarefaView.getTxtIdTarefa().setVisible(true);
        tarefaView.getTxtIdTarefa().setFocusable(false);
        tarefaView.getTxtNome().setEnabled(true);
        tarefaView.getTxtNome().setVisible(true);
        tarefaView.getTxtNome().setFocusable(true);
        tarefaView.getTxtDescricao().setEnabled(true);
        tarefaView.getTxtDescricao().setVisible(true);
        tarefaView.getTxtDescricao().setFocusable(true);
        TarefaDao tarefaDao = new TarefaDao();
        tarefaTemp = tarefaDao.getTarefaId(Integer.parseInt(tarefaView.getTxtIdTarefa().getText()));
        tarefaView.getTxtNome().setText(tarefaTemp.getNome());
        tarefaView.getTxtDescricao().setText(tarefaTemp.getObs());


        if(tarefaTemp.getDataHorarioFinal() == null){
            tarefaView.getBtnFinalizar().setEnabled(true);
            tarefaView.getBtnFinalizar().setVisible(true);
        }else{
            tarefaView.getBtnFinalizar().setEnabled(false);
            tarefaView.getBtnFinalizar().setVisible(true);
        }

    }

    private void layoutPosSalvar(){
        tarefaView.layoutPadrao();
        tarefaView.getTxtNome().setEnabled(true);
        tarefaView.getTxtNome().setFocusable(false);
        tarefaView.getTxtDescricao().setEnabled(true);
        tarefaView.getTxtDescricao().setFocusable(false);


    }

    private void buscarPeloId(){

        TarefaDao tarefaDao = new TarefaDao();
        tarefaTemp = tarefaDao.getTarefaId(Integer.parseInt(tarefaView.getTxtIdTarefa().getText()));
        if (tarefaTemp != null){
            tarefaView.getTxtNome().setText(tarefaTemp.getNome());
            tarefaView.getTxtDescricao().setText(tarefaTemp.getObs());
            if(tarefaTemp.getDataHorarioFinal() != null){
                tarefaView.getLblStatus().setText("Status: Tarefa finalizada!");
            }else{
                tarefaView.getLblStatus().setText("Status: Tarefa não finalizada!");

            }
        }else{
            System.out.println("Error");
        }


    }

    private void iniciarTarefa(){

        tarefaTemp.setDataHorarioInicio(horaAgora.now());
        tarefaView.getBtnIniciar().setEnabled(false);

    }

    private void finalizarTarefa(){
        TarefaDao tarefaDao = new TarefaDao();
        tarefaTemp = tarefaDao.getTarefaId(Integer.parseInt(tarefaView.getTxtIdTarefa().getText()));
        tarefaTemp.setDataHorarioFinal(horaAgora.now());
        System.out.println(tarefaTemp.getDataHorarioFinal());
        tarefaView.getBtnFinalizar().setEnabled(false);

    }

    private void salvar(){
        String idStr = tarefaView.getTxtIdTarefa().getText();
        TarefaDao tarefaDao = new TarefaDao();


        if(idStr == null || idStr.trim().isEmpty()) {

            tarefaTemp.setId(0);
            tarefaTemp.setNome(tarefaView.getTxtNome().getText());
            tarefaTemp.setObs(tarefaView.getTxtDescricao().getText());

            tarefaDao.create(tarefaTemp);

            JOptionPane.showMessageDialog(null,"Tarefa adicionada com sucesso",null,JOptionPane.INFORMATION_MESSAGE);

            layoutBuscarPeloId();
            tarefaTemp = tarefaDao.getTarefaNoFinalized(tarefaTemp.getNome());
            tarefaView.getTxtIdTarefa().setText(Integer.toString(tarefaTemp.getId()));
            buscarPeloId();


        }
        else{

            tarefaTemp.setId(Integer.parseInt(idStr));
            tarefaTemp.setNome(tarefaView.getTxtNome().getText());
            tarefaTemp.setObs(tarefaView.getTxtDescricao().getText());
            //Teste pra ver qual a hora que finalizou a tarefa antes de salvar
            System.out.println(tarefaTemp.getDataHorarioFinal());
            tarefaDao.create(tarefaTemp);
            JOptionPane.showMessageDialog(null,"Tarefa atualizada com sucesso",null,JOptionPane.INFORMATION_MESSAGE);

            layoutBuscarPeloId();
            tarefaTemp = tarefaDao.getTarefaId(Integer.parseInt(tarefaView.getTxtIdTarefa().getText()));
            tarefaView.getTxtIdTarefa().setText(Integer.toString(tarefaTemp.getId()));
            buscarPeloId();
        }






    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String btnAcaoComando = e.getActionCommand().toLowerCase();
        Object source = e.getSource();

        System.out.println(source);
        System.out.println(btnAcaoComando);

        if(source == tarefaView.getTxtIdTarefa() && tarefaView.getTxtIdTarefa().getText() != null){
            layoutBuscarPeloId();
            buscarPeloId();
        }else {

            switch (btnAcaoComando) {
                case "nova tarefa":
                    layoutNovaTarefa();
                    break;
                case "cancelar":
                    cancelar();
                    break;
                case "alterar":
                    layoutAlterar();
                    break;
                case "iniciar tarefa":
                    iniciarTarefa();
                    System.out.println(tarefaTemp.getDataHorarioInicio());
                    break;
                case "salvar tarefa":
                    salvar();
                    break;
                case "finalizar tarefa":
                    finalizarTarefa();
                    break;
            }
        }
    }
}
