package com.vitoraugusto.senac.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.vitoraugusto.senac.R;


public class FrequenciaActivity extends AppCompatActivity {
    ImageView voltar;
    Button btnConfirmar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequencia);

        voltar = findViewById(R.id.voltar);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        voltar.setOnClickListener(v -> {
            Intent intent = new Intent(FrequenciaActivity.this, MainActivity.class);
            startActivity(intent);
        });
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialogCodigo = new Dialog(FrequenciaActivity.this);
                dialogCodigo.setContentView(R.layout.dialog_codigo);
                dialogCodigo.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                EditText editCodigo = dialogCodigo.findViewById(R.id.editCodigo);
                Button btnEnviarCodigo = dialogCodigo.findViewById(R.id.btnEnviarCodigo);

                btnEnviarCodigo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String codigo = editCodigo.getText().toString().trim();

                        if (codigo.length() == 3) {
                            dialogCodigo.dismiss();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Dialog confirmadoDialog = new Dialog(FrequenciaActivity.this);
                                    confirmadoDialog.setContentView(R.layout.frequencia_confirmada);
                                    confirmadoDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                    confirmadoDialog.show();
                                }
                            }, 1000);

                        } else {
                            editCodigo.setError("Digite 3 números");
                        }
                    }
                });

                dialogCodigo.show();
            }
        });
    }
}

