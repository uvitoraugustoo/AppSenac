package com.vitoraugusto.senac.controller;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedController {

    private static final String PREFS_NAME = "user_prefs";
    private SharedPreferences prefs;

    private static final String KEY_NOME = "nome";
    private static final String KEY_CPF = "cpf";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_RA = "ra";
    private static final String KEY_TURNO = "turno";

    public SharedController(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void salvarDados(String nome, String cpf, String senha, String ra, String turno) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NOME, nome);
        editor.putString(KEY_CPF, cpf);
        editor.putString(KEY_SENHA, senha);
        editor.putString(KEY_RA, ra);
        editor.putString(KEY_TURNO, turno);
        editor.apply();
    }

    public String getNome() {
        return prefs.getString(KEY_NOME, "");
    }

    public String getCpf() {
        return prefs.getString(KEY_CPF, "");
    }

    public String getSenha() {
        return prefs.getString(KEY_SENHA, "");
    }

    public String getRa() {
        return prefs.getString(KEY_RA, "");
    }

    public String getTurno() {
        return prefs.getString(KEY_TURNO, "");
    }
}
