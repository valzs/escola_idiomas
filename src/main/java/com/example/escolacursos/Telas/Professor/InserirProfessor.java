package com.example.escolacursos.Telas.Professor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

public class InserirProfessor extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_professor);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.editText);
                EditText formacao = (EditText)findViewById(R.id.editText2);
                EditText preco = (EditText)findViewById(R.id.editText3);

                String nomeString = nome.getText().toString();
                String formacaoString = formacao.getText().toString();
                String precoString = preco.getText().toString();
                String resultado;

                resultado = MainActivity.proCont.inserirProfessor(nomeString, formacaoString, precoString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
