package com.vitoraugusto.senac.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vitoraugusto.senac.R;
import com.vitoraugusto.senac.controller.DbController;
import com.vitoraugusto.senac.model.Pessoa;

public class LoginActivity extends AppCompatActivity {
    private EditText cpf, senha;
    private Button btnLogin;
    private ImageView olhoSenha;
    private boolean senhaVisivel = false;
    private DbController dbController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpf = findViewById(R.id.cpf);
        senha = findViewById(R.id.senha);
        btnLogin = findViewById(R.id.realizarC); // esse botão agora é de login
        olhoSenha = findViewById(R.id.olhoSenha);
        dbController = new DbController(this);

        btnLogin.setOnClickListener(v -> {
            String cp = cpf.getText().toString().trim();
            String senh = senha.getText().toString().trim();

            if (cp.isEmpty() || senh.isEmpty()) {
                Toast.makeText(this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show();
            } else if (cp.length() != 11) {
                Toast.makeText(this, "CPF inválido. Deve conter 11 números.", Toast.LENGTH_SHORT).show();
            } else {
                // Verifica se existe essa pessoa no banco
                Pessoa pessoa = dbController.validarLogin(cp, senh);
                if (pessoa != null) {
                    Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                    // Envia os dados para a MainActivity
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("nome", pessoa.getNome());
                    intent.putExtra("cpf", pessoa.getCpf());
                    intent.putExtra("ra", pessoa.getRa());
                    intent.putExtra("turno", pessoa.getTurno());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "CPF ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        olhoSenha.setOnClickListener(v -> {
            if (senhaVisivel) {
                senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                olhoSenha.setImageResource(R.drawable.olho_senha);
                senhaVisivel = false;
            } else {
                senha.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
                olhoSenha.setImageResource(R.drawable.olho_aberto_senha);
                senhaVisivel = true;
            }
            senha.setSelection(senha.getText().length());
        });
    }
}
