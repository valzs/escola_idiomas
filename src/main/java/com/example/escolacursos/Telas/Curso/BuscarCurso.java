package com.example.escolacursos.Telas.Curso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;


public class BuscarCurso extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_curso);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tipo = (EditText)findViewById(R.id.tipoTxt);
                String tipoString = tipo.getText().toString();

                Intent intent = new Intent(BuscarCurso.this, ConsultarCurso.class);
                intent.putExtra("tipo", tipoString);
                startActivity(intent);
                finish();
            }
        });
    }
}
