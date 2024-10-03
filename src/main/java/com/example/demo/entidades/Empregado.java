package com.example.demo.entidades;

public class Empregado {
    private int idEmpregado;   
    private String nomeEmpregado;
    private String matriculaEmpregado;
    private String emailEmpregado;
    private Setor setor;

    

    public int getIdEmpregado() {
        return this.idEmpregado;
    }

    public void setIdEmpregado(int idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public String getNomeEmpregado() {
        return this.nomeEmpregado;
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getMatriculaEmpregado() {
        return this.matriculaEmpregado;
    }

    public void setMatriculaEmpregado(String matriculaEmpregado) {
        this.matriculaEmpregado = matriculaEmpregado;
    }

    public String getEmailEmpregado() {
        return this.emailEmpregado;
    }

    public void setEmailEmpregado(String emailEmpregado) {
        this.emailEmpregado = emailEmpregado;
    }

    public Setor getSetor() {
        return this.setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
