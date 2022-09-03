package com.example.escolacursos.Telas.Professor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

public class AlterarProfessor extends Activity {

    EditText nome;
    EditText formacao;
    EditText preco;
    Button alterar;
    Button deletar;
    Cursor cursor;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.atualizar_professor);

        codigo = this.getIntent().getStringExtra("codigo");

        nome = (EditText) findViewById(R.id.editText4);
        formacao = (EditText) findViewById(R.id.editText5);
        preco = (EditText) findViewById(R.id.editText6);

        alterar = (Button) findViewById(R.id.button2);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.proCont.alterarProfessor(Integer.parseInt(codigo),nome.getText().toString(), formacao.getText().toString(), preco.getText().toString());
                Intent intent = new Intent(AlterarProfessor.this, ConsultarProfessor.class);
                startActivity(intent);
                finish();
            }
        });

        cursor = MainActivity.proCont.carregarProfessorPorID(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("_nome")));
        formacao.setText(cursor.getString(cursor.getColumnIndexOrThrow("_formacao")));
        preco.setText(cursor.getString(cursor.getColumnIndexOrThrow("_preco")));

        deletar = (Button) findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.proCont.deletarProfessor(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarProfessor.this, ConsultarProfessor.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
