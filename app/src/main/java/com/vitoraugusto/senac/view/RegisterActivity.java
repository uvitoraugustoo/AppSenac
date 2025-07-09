package com.vitoraugusto.senac.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.vitoraugusto.senac.R;
import com.vitoraugusto.senac.controller.DbController;
import com.vitoraugusto.senac.controller.PessoaController;
import com.vitoraugusto.senac.model.Pessoa;


public class RegisterActivity extends AppCompatActivity {
    private EditText nome, cpf, senha, ra, turno;
    private Button cadastrar;
    private Pessoa pessoa;

    private PessoaController pessoaController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        pessoaController = new PessoaController(this);

        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        senha = findViewById(R.id.senha);
        ra = findViewById(R.id.ra);
        turno = findViewById(R.id.turno);
        cadastrar = findViewById(R.id.realizarC);

        cadastrar.setOnClickListener(v -> {

            String nom = nome.getText().toString().trim();
            String cp = cpf.getText().toString().trim();
            String senh = senha.getText().toString().trim();
            String rA = ra.getText().toString().trim();
            String turn = turno.getText().toString().trim();

            if (nom.isEmpty() || cp.isEmpty() || senh.isEmpty() || rA.isEmpty() || turn.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show();
            } else if (cp.length() != 11) {
                Toast.makeText(RegisterActivity.this, "O CPF está incorreto, deve conter 11 números", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Cadastro Finalizado!!", Toast.LENGTH_SHORT).show();

                pessoa = new Pessoa(nom, cp, senh, rA, turn);
                pessoaController.salvarPessoa(pessoa);

                DbController dbController = new DbController(this);
                String resultado = dbController.insertData(nom, cp, senh, rA, turn);
                Toast.makeText(RegisterActivity.this, resultado, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }
}