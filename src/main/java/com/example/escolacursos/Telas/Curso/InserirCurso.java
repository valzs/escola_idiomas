package com.example.escolacursos.Telas.Curso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

public class InserirCurso extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_curso);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tipo = (EditText)findViewById(R.id.tipoTxt);
                EditText horario = (EditText)findViewById(R.id.horarioTxt);
                EditText dia = (EditText)findViewById(R.id.diaTxt);

                String tipoString = tipo.getText().toString();
                String horarioString = horario.getText().toString();
                String diaString = dia.getText().toString();
                String resultado;

                resultado = MainActivity.curCont.inserirCurso(tipoString, horarioString, diaString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
