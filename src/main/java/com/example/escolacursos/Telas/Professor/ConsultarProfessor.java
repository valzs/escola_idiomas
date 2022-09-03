package com.example.escolacursos.Telas.Professor;

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

public class ConsultarProfessor extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_professor);

        final Cursor cursor = MainActivity.proCont.carregarProfessor();
        String[] nomeCampos = new String[]{"_id", "_nome", "_formacao", "_preco"};
        int[] idViews = new int[] { R.id.idProfessor, R.id.nomeProfessor, R.id.formacaoProfessor, R.id.precoProfessor};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.professor_layout, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultarProfessor.this, AlterarProfessor.class);
                intent.putExtra("codigo", codigo);

                startActivity(intent);
                finish();
            }
        });

    }
}
