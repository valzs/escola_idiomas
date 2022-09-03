package com.example.escolacursos.Telas.Curso;

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

public class ConsultarCurso extends Activity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_curso);

        final Cursor cursor = MainActivity.curCont.carregarCurso();
        String[] tipoCampos = new String[]{"_id", "_tipo", "_horario","_dia"};
        int[] idViews = new int[] { R.id.idCurso, R.id.tipoCurso, R.id.horario, R.id.diaTxt};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.curso_layout, cursor, tipoCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView2);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultarCurso.this, AlterarCurso.class);
                intent.putExtra("codigo", codigo);

                startActivity(intent);
                finish();
            }
        });

    }
}
