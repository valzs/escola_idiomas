package com.example.escolacursos.Telas.Aluno;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

public class AlterarAluno extends Activity {

    EditText nome;
    EditText idade;
    EditText objetivo;
    Button alterar;
    Button deletar;
    Cursor cursor;
    String codigo;

    @Override
   protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.atualizar_aluno);

        codigo = this.getIntent().getStringExtra("codigo");
        nome = (EditText) findViewById(R.id.nomeTxt);
        idade = (EditText) findViewById(R.id.idadeTxt);
        objetivo = (EditText) findViewById(R.id.objetivoTxt);

        alterar = (Button) findViewById(R.id.alterarTxt);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.aluCont.alterarAluno(Integer.parseInt(codigo), nome.getText().toString(), idade.getText().toString(), objetivo.getText().toString());
                Intent intent = new Intent(AlterarAluno.this, ConsultarAluno.class);
                startActivity(intent);
                finish();
            }
        });

        cursor = MainActivity.aluCont.carregarAlunoPorID(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("_nome")));
        idade.setText(cursor.getString(cursor.getColumnIndexOrThrow("_idade")));
        objetivo.setText(cursor.getString(cursor.getColumnIndexOrThrow("_objetivo")));

        deletar = (Button) findViewById(R.id.deletarTxt);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.aluCont.deletarAluno(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarAluno.this, ConsultarAluno.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
