package com.example.escolacursos.Telas.Aluno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;


public class BuscarAluno extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_aluno);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.nomeTxt);
                String nomeString = nome.getText().toString();

                Intent intent = new Intent(BuscarAluno.this, ConsultarAlunoParametro.class);
                intent.putExtra("nome", nomeString);
                startActivity(intent);
                finish();
            }
        });
    }
}
