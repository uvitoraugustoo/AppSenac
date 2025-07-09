package com.vitoraugusto.senac.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class BancoDeDadosRegister extends SQLiteOpenHelper {

        private static final String NOME_DATABASE = "dados_usuarios_registro";
        private static final int VERSAO_DATABASE = 3;  // aumente a versão quando alterar a tabela

        public static final String NOME_TABELA = "pessoa";

        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String CPF = "cpf";
        public static final String SENHA = "senha";
        public static final String RA = "ra";
        public static final String TURNO = "turno";

        private static final String SQL_CRIAR_TABELA =
                "CREATE TABLE " + NOME_TABELA + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOME + " TEXT, " +
                        CPF + " TEXT, " +
                        SENHA + " TEXT, " +
                        RA + " TEXT, "+          // Corrigido espaço aqui
                        TURNO + " TEXT)";

        public BancoDeDadosRegister(Context context) {
            super(context, NOME_DATABASE, null, VERSAO_DATABASE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("BancoDeDadosRegister", "Criando tabela pessoa");
            db.execSQL(SQL_CRIAR_TABELA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("BancoDeDadosRegister", "Atualizando banco da versão " + oldVersion + " para " + newVersion);
            db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
            onCreate(db);
        }
    }

