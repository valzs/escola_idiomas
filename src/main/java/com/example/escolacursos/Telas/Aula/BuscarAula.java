package com.example.escolacursos.Telas.Aula;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Professor.ConsultarProfessorParametro;


public class BuscarAula extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_aula);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.editText);
                String observacaoString = nome.getText().toString();

                Intent intent = new Intent(BuscarAula.this, ConsultarAula.class);
                intent.putExtra("observacao", observacaoString);
                startActivity(intent);
                finish();
            }
        });
    }
}
