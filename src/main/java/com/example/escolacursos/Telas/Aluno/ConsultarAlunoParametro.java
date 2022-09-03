package com.example.escolacursos.Telas.Aluno;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;
import com.example.escolacursos.Telas.Professor.AlterarProfessor;
import com.example.escolacursos.Telas.Professor.ConsultarProfessorParametro;

public class ConsultarAlunoParametro extends Activity {

    private ListView lista;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_aluno);
        nome = this.getIntent().getStringExtra("nome");

        final Cursor cursor = MainActivity.aluCont.carregarAlunoPorNumero(nome);
        String[] nomeCampos = new String[]{"_id", "_nome", "_idade","_objetivo"};
        int[] idViews = new int[] { R.id.idAluno, R.id.nomeAluno, R.id.idade,R.id.objetivoTxt};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.aluno_layout, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView2);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultarAlunoParametro.this, AlterarAluno.class);
                intent.putExtra("codigo", codigo);

                startActivity(intent);
                finish();
            }
        });
    }
}