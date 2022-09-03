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

public class ConsultarAluno extends Activity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_aluno);

        final Cursor cursor = MainActivity.aluCont.carregarAluno();
        String[] nomeCampos = new String[]{"_id", "_nome", "_idade","_objetivo"};
        int[] idViews = new int[] { R.id.idAluno, R.id.nomeAluno, R.id.idade, R.id.objetivoTxt};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.aluno_layout, cursor, nomeCampos, idViews, 0);
        lista = (android.widget.ListView) findViewById(R.id.listView2);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultarAluno.this, AlterarAluno.class);
                intent.putExtra("codigo", codigo);

                startActivity(intent);
                finish();
            }
        });

    }
}
