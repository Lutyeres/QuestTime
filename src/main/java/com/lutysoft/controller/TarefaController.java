package com.lutysoft.controller;

import com.lutysoft.model.dao.TarefaDao;
import com.lutysoft.model.entiny.Tarefa;
import com.lutysoft.model.entiny.Usuario;
import com.lutysoft.view.LoginView;
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

        if(tarefaTemp != null){
            tarefaTemp.limparTarefa();
        }else{
            tarefaTemp = new Tarefa();
        }
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
        tarefaView.getTxtNome().setEditable(true);
        tarefaView.getTxtDescricao().setEnabled(true);
        tarefaView.getTxtDescricao().setVisible(true);
        tarefaView.getTxtDescricao().setFocusable(true);
        tarefaView.getTxtDescricao().setEditable(true);
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
            JOptionPane.showMessageDialog(null,"Tarefa não encontrada!",null,JOptionPane.INFORMATION_MESSAGE);
            //tarefaView.layoutPadrao();
        }


    }

    private void iniciarTarefa(){

        tarefaTemp.setDataHorarioInicio(horaAgora.now());
        tarefaView.getBtnIniciar().setEnabled(false);

    }

    private void finalizarTarefa(){
        TarefaDao tarefaDao = new TarefaDao();
        System.out.println(String.format("ID: %s\nNome: %s\nDescrição: %s\nInico da Tarefa: %s\nFinalização da Tarefa: %s\n-------------", tarefaTemp.getId(),tarefaTemp.getNome(),tarefaTemp.getObs(),tarefaTemp.getDataHorarioInicio(),tarefaTemp.getDataHorarioFinal()));
        tarefaTemp = tarefaDao.getTarefaId(Integer.parseInt(tarefaView.getTxtIdTarefa().getText()));
        System.out.println(String.format("ID: %s\nNome: %s\nDescrição: %s\nInico da Tarefa: %s\nFinalização da Tarefa: %s\n-------------", tarefaTemp.getId(),tarefaTemp.getNome(),tarefaTemp.getObs(),tarefaTemp.getDataHorarioInicio(),tarefaTemp.getDataHorarioFinal()));
        tarefaTemp.setDataHorarioFinal(horaAgora.now());
        System.out.println(String.format("ID: %s\nNome: %s\nDescrição: %s\nInico da Tarefa: %s\nFinalização da Tarefa: %s\n-------------", tarefaTemp.getId(),tarefaTemp.getNome(),tarefaTemp.getObs(),tarefaTemp.getDataHorarioInicio(),tarefaTemp.getDataHorarioFinal()));
        tarefaView.getBtnFinalizar().setEnabled(false);

    }

    private void salvar(){
        String idStr = tarefaView.getTxtIdTarefa().getText();
        TarefaDao tarefaDao = new TarefaDao();


        if(idStr == null || idStr.trim().isEmpty()) {

            tarefaTemp.setId(0);
            tarefaTemp.setIdUsuarioTarefa(Usuario.getUsuarioLogado().getId());
            tarefaTemp.setNome(tarefaView.getTxtNome().getText());
            tarefaTemp.setObs(tarefaView.getTxtDescricao().getText());
            if(tarefaTemp.getDataHorarioInicio() != null) {
                String message = tarefaDao.create(tarefaTemp);
                System.out.println(message);
                JOptionPane.showMessageDialog(null, message, null, JOptionPane.INFORMATION_MESSAGE);
                layoutBuscarPeloId();
                tarefaTemp = tarefaDao.getTarefaNoFinalized(tarefaTemp.getNome());
                tarefaView.getTxtIdTarefa().setText(Integer.toString(tarefaTemp.getId()));
                buscarPeloId();
            }else{
                String message = tarefaDao.create(tarefaTemp);
                JOptionPane.showMessageDialog(null, "Inicie a tarefa antes de salva-la\n" + message, null, JOptionPane.INFORMATION_MESSAGE);

            }

        }
        else{

            tarefaTemp.setId(Integer.parseInt(idStr));
            tarefaTemp.setIdUsuarioTarefa(Usuario.getUsuarioLogado().getId());
            tarefaTemp.setNome(tarefaView.getTxtNome().getText());
            tarefaTemp.setObs(tarefaView.getTxtDescricao().getText());

            String message = tarefaDao.create(tarefaTemp);
            System.out.println(message);
            JOptionPane.showMessageDialog(null,message,null,JOptionPane.INFORMATION_MESSAGE);
            layoutBuscarPeloId();
            tarefaTemp = tarefaDao.getTarefaId(Integer.parseInt(tarefaView.getTxtIdTarefa().getText()));
            tarefaView.getTxtIdTarefa().setText(Integer.toString(tarefaTemp.getId()));
            buscarPeloId();
            System.out.println(String.format("ID Usuario: %d\nID: %s\nNome: %s\nDescrição: %s\nInico da Tarefa: %s\nFinalização da Tarefa: %s\n-------------",tarefaTemp.getIdUsuarioTarefa(), tarefaTemp.getId(),tarefaTemp.getNome(),tarefaTemp.getObs(),tarefaTemp.getDataHorarioInicio(),tarefaTemp.getDataHorarioFinal()));
        }
    }

    private void excluir(){
        System.out.println(String.format("ID Usuario: %d\nID: %s\nNome: %s\nDescrição: %s\nInico da Tarefa: %s\nFinalização da Tarefa: %s\n-------------",tarefaTemp.getIdUsuarioTarefa(), tarefaTemp.getId(),tarefaTemp.getNome(),tarefaTemp.getObs(),tarefaTemp.getDataHorarioInicio(),tarefaTemp.getDataHorarioFinal()));
        TarefaDao tarefaDao = new TarefaDao();
        String message = tarefaDao.delete(tarefaTemp);
        JOptionPane.showMessageDialog(null,message,null,JOptionPane.INFORMATION_MESSAGE);
        System.out.println(String.format("ID: %s\nNome: %s\nDescrição: %s\nInico da Tarefa: %s\nFinalização da Tarefa: %s\n-------------", tarefaTemp.getId(),tarefaTemp.getNome(),tarefaTemp.getObs(),tarefaTemp.getDataHorarioInicio(),tarefaTemp.getDataHorarioFinal()));
        tarefaView.layoutPadrao();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String btnAcaoComando = e.getActionCommand().toLowerCase();
        Object source = e.getSource();

        System.out.println(source);
        System.out.println(btnAcaoComando);

        if(source == tarefaView.getTxtIdTarefa() && tarefaView.getTxtIdTarefa().getText() != null){
            buscarPeloId();
            if(tarefaTemp != null){
                layoutBuscarPeloId();
            }else{
                tarefaView.layoutPadrao();
            }
        }else {

            switch (btnAcaoComando) {
                case "nova tarefa":
                    System.out.println(Usuario.getUsuarioLogado().getNome());
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
                case "excluir":
                    excluir();
                    break;
            }
        }
    }
}
