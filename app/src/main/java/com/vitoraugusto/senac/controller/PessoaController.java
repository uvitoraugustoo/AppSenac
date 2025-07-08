package com.vitoraugusto.senac.controller;






import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vitoraugusto.senac.model.Pessoa;


public class PessoaController {



    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;
    public static final String NOMES_PREFERENCES = "usuarios";
    public static final String LOGIN_PREFERENCES ="dados_login";

    public PessoaController(Context context) {
        sharedPreferences = context.getSharedPreferences(NOMES_PREFERENCES, 0);
        sharedPreferences2 = context.getSharedPreferences(LOGIN_PREFERENCES, 0);
    }


    public void salvarPessoa(Pessoa pessoa) {
        SharedPreferences.Editor listaVip = sharedPreferences.edit();
        listaVip.putString("nome", pessoa.getNome());
        listaVip.putString("email", pessoa.getEmail());
        listaVip.putString("senha", pessoa.getSenha());
        listaVip.putString("cpf", pessoa.getCpf());
        listaVip.apply();
    }
    public void salvarLogin(Pessoa pessoa) {
        SharedPreferences.Editor loginVip = sharedPreferences.edit();
        loginVip.putString("email", pessoa.getEmail());
        loginVip.putString("senha", pessoa.getSenha());
        loginVip.apply();
    }

    public Pessoa carregarPessoa() {
        String nome = sharedPreferences.getString("nome", "");
        String email = sharedPreferences.getString("email", "");
        String senha = sharedPreferences.getString("senha", "");
        String cpf = sharedPreferences.getString("cpf", "");
        return new Pessoa(nome, email, senha,cpf);
    }
    public Pessoa carregarLogin() {
        String email = sharedPreferences.getString("email", "");
        String senha = sharedPreferences.getString("senha", "");
        return new Pessoa( email, senha);
    }

    @NonNull
    public String toString() {
        Log.d("MVC Controller ", "Controller Iniciado...");
        return super.toString();

    }

}



