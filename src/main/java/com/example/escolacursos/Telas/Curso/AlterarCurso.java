package com.example.escolacursos.Telas.Curso;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

public class AlterarCurso extends Activity {

    EditText tipo;
    EditText horario;
    EditText dia;
    Button alterar;
    Button deletar;
    Cursor cursor;
    String codigo;

    @Override
   protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.atualizar_curso);

        codigo = this.getIntent().getStringExtra("codigo");
        tipo = (EditText) findViewById(R.id.tipoTxt);
        horario = (EditText) findViewById(R.id.horarioTxt);
        dia = (EditText) findViewById(R.id.diaTxt);

        alterar = (Button) findViewById(R.id.alterarTxt);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.curCont.alterarCurso(Integer.parseInt(codigo), tipo.getText().toString(), horario.getText().toString(), dia.getText().toString());
                Intent intent = new Intent(AlterarCurso.this, ConsultarCurso.class);
                startActivity(intent);
                finish();
            }
        });

        cursor = MainActivity.curCont.carregarCursoPorID(Integer.parseInt(codigo));
        tipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("_tipo")));
        horario.setText(cursor.getString(cursor.getColumnIndexOrThrow("_horario")));
        dia.setText(cursor.getString(cursor.getColumnIndexOrThrow("_dia")));

        deletar = (Button) findViewById(R.id.deletarTxt);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.curCont.deletarCurso(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarCurso.this, ConsultarCurso.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
