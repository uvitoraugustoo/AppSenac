package com.vitoraugusto.senac.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitoraugusto.senac.R;

public class MainActivity extends AppCompatActivity {

    private TextView tvNome, tvCpf, tvRa, tvTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNome = findViewById(R.id.tvNome);
        tvCpf = findViewById(R.id.tvCpf);
        tvRa = findViewById(R.id.tvRa);
        tvTurno = findViewById(R.id.tvTurno);

        String nome = getIntent().getStringExtra("nome");
        String cpf = getIntent().getStringExtra("cpf");
        String ra = getIntent().getStringExtra("ra");
        String turno = getIntent().getStringExtra("turno");

        tvNome.setText(nome);
        tvCpf.setText("CPF: " + cpf);
        tvRa.setText("RA: " + ra);
        tvTurno.setText("Turno: " + turno);
    }
}
