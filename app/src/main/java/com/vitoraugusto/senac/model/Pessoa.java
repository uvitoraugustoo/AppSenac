package com.vitoraugusto.senac.model;



import android.widget.EditText;

public class Pessoa {
    private String nome;

    private String cpf;

    private String senha;

    private String ra;
            private String turno;

    public Pessoa(String nome, String cpf, String senha, String ra, String turno) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.ra = ra;
        this.turno = turno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Pessoa(){

    }

    public Pessoa(String cpf, String senha) {
   this.cpf = cpf;
        this.senha = senha;
    }












    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
