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
        listaVip.putString("cpf", pessoa.getCpf());
        listaVip.putString("senha", pessoa.getSenha());
        listaVip.apply();
    }

    @NonNull
    public String toString() {
        Log.d("MVC Controller ", "Controller Iniciado...");
        return super.toString();

    }
}



