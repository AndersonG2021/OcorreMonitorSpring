package com.example.demo.entidades;

import java.util.Date;

public class Falha {
    private int idFalha;
    private String tipoFalha;
    private Date dataOcorrido;
    private Setor setor;


    public int getIdFalha() {
        return this.idFalha;
    }

    public void setIdFalha(int idFalha) {
        this.idFalha = idFalha;
    }

    public String getTipoFalha() {
        return this.tipoFalha;
    }

    public void setTipoFalha(String tipoFalha) {
        this.tipoFalha = tipoFalha;
    }

    public Date getDataOcorrido() {
        return this.dataOcorrido;
    }

    public void setDataOcorrido(Date dataOcorrido) {
        this.dataOcorrido = dataOcorrido;
    }

    public Setor getSetor() {
        return this.setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
