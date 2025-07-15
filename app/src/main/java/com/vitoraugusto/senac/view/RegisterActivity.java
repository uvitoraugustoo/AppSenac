package com.vitoraugusto.senac.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.vitoraugusto.senac.R;
import com.vitoraugusto.senac.controller.DbController;
import com.vitoraugusto.senac.controller.PessoaController;
import com.vitoraugusto.senac.controller.SharedController;
import com.vitoraugusto.senac.model.Pessoa;


public class RegisterActivity extends AppCompatActivity {
    private EditText nome, cpf, senha, ra;
    Spinner turno;
    private Button cadastrar;
    private Pessoa pessoa;
    private ImageView olhoSenha;

    private SharedController sharedController;
    private Boolean senhaVisivel = true;
    private PessoaController pessoaController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        sharedController = new SharedController(this);
        pessoaController = new PessoaController(this);

        olhoSenha = findViewById(R.id.olhoSenha);
        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        senha = findViewById(R.id.senha);
        ra = findViewById(R.id.ra);
        turno = findViewById(R.id.turno);
        cadastrar = findViewById(R.id.realizarC);

        String[] turnos = {"Selecione o turno", "Manhã", "Tarde", "Noite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                turnos
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        turno.setAdapter(adapter);

        carregarDados();
        cadastrar.setOnClickListener(v -> {

            String nom = nome.getText().toString().trim();
            String cp = cpf.getText().toString().trim();
            String senh = senha.getText().toString().trim();
            String rA = ra.getText().toString().trim();
            String turn = turno.getSelectedItem().toString();


            if (nom.isEmpty() || cp.isEmpty() || senh.isEmpty() || rA.isEmpty() || turn.equals("Selecione o turno")) {
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

                sharedController.salvarDados(nom, cp, senh, rA, turn);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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

    private void carregarDados() {
        nome.setText(sharedController.getNome());
        cpf.setText(sharedController.getCpf());
        senha.setText(sharedController.getSenha());
        ra.setText(sharedController.getRa());
        String turnoSalvo = sharedController.getTurno();
        ArrayAdapter adapter = (ArrayAdapter) turno.getAdapter();
        int posicao = adapter.getPosition(turnoSalvo);
        turno.setSelection(posicao);
    }
}