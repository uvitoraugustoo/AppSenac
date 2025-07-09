package com.vitoraugusto.senac.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitoraugusto.senac.R;
import com.vitoraugusto.senac.controller.SharedController;

public class MainActivity extends AppCompatActivity {
    private SharedController sharedController;
    private TextView tvNome, tvCpf, tvRa, tvTurno;
    private ImageView logout, iconePessoa, voltar;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedController = new SharedController(this);
        tvNome = findViewById(R.id.tvNome);
        tvCpf = findViewById(R.id.tvCpf);
        tvRa = findViewById(R.id.tvRa);
        tvTurno = findViewById(R.id.tvTurno);
        logout = findViewById(R.id.logout);
        iconePessoa = findViewById(R.id.icone_pessoa);

        String nome = sharedController.getNome();
        String cpf = sharedController.getCpf();
        String ra = sharedController.getRa();
        String turno = sharedController.getTurno();

        tvNome.setText(nome);
        tvCpf.setText("CPF: " + cpf);
        tvRa.setText("RA: " + ra);
        tvTurno.setText("Turno: " + turno);

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        iconePessoa.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FrequenciaActivity.class);
            startActivity(intent);
        });
    }
    }

