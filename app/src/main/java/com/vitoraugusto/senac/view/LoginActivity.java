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
import com.vitoraugusto.senac.controller.PessoaController;
import com.vitoraugusto.senac.model.Pessoa;

public class LoginActivity {




    public class LoginActivity extends AppCompatActivity {
        private EditText nome, cpf, senha, email;
        private Button cadastrar, limpar;
        private Pessoa pessoa;
        private TextView loginTrue;
        private PessoaController pessoaController;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_login);
            pessoaController = new PessoaController(this);

            nome = findViewById(R.id.nome);
            cpf = findViewById(R.id.cpf);
            senha = findViewById(R.id.senha);
            email = findViewById(R.id.email);
            cadastrar = findViewById(R.id.realizarC);
            limpar = findViewById(R.id.limpar);
            loginTrue = findViewById(R.id.loginTrue);

            cadastrar.setOnClickListener(v -> {

                String nom = nome.getText().toString().trim();
                String emai = email.getText().toString().trim();
                String senh = senha.getText().toString().trim();
                String cp = cpf.getText().toString().trim();

                if (    nom.isEmpty() || cp.isEmpty() || emai.isEmpty() || senh.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show();
                } else if (cpf.length() != 11) {
                    Toast.makeText(RegisterActivity.this, "O CPF esta incorreto, o CPF deve conter 11 numeros", Toast.LENGTH_SHORT).show();
                }else if (!emailValido((emai))) {
                    Toast.makeText(this, "Digite um email válido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Cadastro Finalizado!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    DbController dbController = new DbController(this);
                    String resultado;

                    pessoa = new Pessoa(
                            nome.getText().toString(),
                            email.getText().toString(),
                            senha.getText().toString(),
                            cpf.getText().toString()
                    );
                    pessoaController.salvarPessoa(pessoa);
                    resultado = dbController.insertData(pessoa.getNome(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getCpf());
                    Toast.makeText(RegisterActivity.this, "Dados Salvos", Toast.LENGTH_SHORT).show();
                }
            });
            loginTrue.setOnClickListener(v -> {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            });

            limpar.setOnClickListener(v -> {
                nome.setText("");
                email.setText("");
                senha.setText("");
                cpf.setText("");
            });

        }
        public boolean emailValido(String email) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

}
