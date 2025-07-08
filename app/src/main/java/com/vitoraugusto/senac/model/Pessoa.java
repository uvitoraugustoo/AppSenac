package com.vitoraugusto.senac.model;



import android.widget.EditText;

public class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    public Pessoa(){

    }
    public Pessoa(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Pessoa(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
