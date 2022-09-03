package com.example.escolacursos.Telas.Professor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;


public class BuscarProfessor extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_professor);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.editText);
                String nomeString = nome.getText().toString();

                Intent intent = new Intent(BuscarProfessor.this, ConsultarProfessorParametro.class);
                intent.putExtra("nome", nomeString);
                startActivity(intent);
                finish();
            }
        });
    }
}
