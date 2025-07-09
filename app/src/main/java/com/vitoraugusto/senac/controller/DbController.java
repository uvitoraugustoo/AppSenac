package com.vitoraugusto.senac.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vitoraugusto.senac.BancoDeDados.BancoDeDadosRegister;
import com.vitoraugusto.senac.model.Pessoa;

public class DbController {
    private SQLiteDatabase db;
    private BancoDeDadosRegister banco;

    public DbController(Context context) {
        banco = new BancoDeDadosRegister(context);
    }


    public String insertData(String nome, String cpf, String senha, String ra, String turno) {
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();

        valores.put("nome", nome);
        valores.put("cpf", cpf);
        valores.put("senha", senha);
        valores.put("ra", ra);
        valores.put("turno", turno);

        long resultado = db.insert("pessoa", null, valores);

        if (resultado == -1) {
            return "Erro ao inserir dados";
        } else {
            return "Cadastro realizado com sucesso";
        }
    }

    public Pessoa validarLogin(String cpf, String senha) {
        db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM pessoa WHERE cpf = ? AND senha = ?", new String[]{cpf, senha});

        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            String ra = cursor.getString(cursor.getColumnIndexOrThrow("ra"));
            String turno = cursor.getString(cursor.getColumnIndexOrThrow("turno"));

            cursor.close();
            return new Pessoa(nome, cpf, senha, ra, turno);
        } else {
            cursor.close();
            return null;
        }
    }

}


