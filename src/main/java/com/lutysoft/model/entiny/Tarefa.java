package com.lutysoft.model.entiny;

import java.time.LocalDateTime;

public class Tarefa {

    private LocalDateTime dataHorarioInicio;
    private LocalDateTime dataHorarioFinal;
    private int id;
    private String nome;
    private String obs;

    public Tarefa() {
    }

    public Tarefa(LocalDateTime dataHorarioInicio, LocalDateTime dataHorarioFinal, int id, String nome, String obs) {
        this.dataHorarioInicio = dataHorarioInicio;
        this.dataHorarioFinal = dataHorarioFinal;
        this.id = id;
        this.nome = nome;
        this.obs = obs;
    }

    public LocalDateTime getDataHorarioInicio() {
        return dataHorarioInicio;
    }

    public void setDataHorarioInicio(LocalDateTime dataHorarioInicio) {
        this.dataHorarioInicio = dataHorarioInicio;
    }

    public LocalDateTime getDataHorarioFinal() {
        return dataHorarioFinal;
    }

    public void setDataHorarioFinal(LocalDateTime dataHorarioFinal) {
        this.dataHorarioFinal = dataHorarioFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
