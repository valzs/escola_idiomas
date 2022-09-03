package com.example.escolacursos.Telas.Aluno;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

public class InserirAluno extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_aluno);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.nomeTxt);
                EditText idade = (EditText)findViewById(R.id.idadeTxt);
                EditText objetivo = (EditText)findViewById(R.id.objetivoTxt);

                String nomeString = nome.getText().toString();
                String idadeString = idade.getText().toString();
                String objetivoString = objetivo.getText().toString();
                String resultado;

                resultado = MainActivity.aluCont.inserirAluno(nomeString, idadeString, objetivoString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
