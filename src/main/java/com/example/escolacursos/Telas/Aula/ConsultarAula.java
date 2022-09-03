package com.example.escolacursos.Telas.Aula;

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

public class ConsultarAula extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_aula);

        final Cursor cursor = MainActivity.aulCont.carregarAula();

        String[] nomeCampos = new String[]{"_id", "_idProfessor", "_idAluno","_idCurso", "_observacao"};
        int [] idViews = new int[] {R.id.id, R.id.idPro, R.id.idAlu,R.id.idCur, R.id.observacao};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.aula_layout, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView3);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo, codigoPro, codigoAlu, codigoCur;
                long teste, teste2, teste3;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                teste = cursor.getLong(cursor.getColumnIndexOrThrow("_idProfessor"));
                teste2 = cursor.getLong(cursor.getColumnIndexOrThrow("_idAluno"));
                teste3 = cursor.getLong(cursor.getColumnIndexOrThrow("_idCurso"));
                codigoPro = Long.toString(teste);
                codigoAlu = Long.toString(teste2);
                codigoCur = Long.toString(teste3);
                Intent intent = new Intent(ConsultarAula.this, AlterarAula.class);
                intent.putExtra("codigo", codigo);
                intent.putExtra("codigoPro", codigoPro);
                intent.putExtra("codigoAlu", codigoAlu);
                intent.putExtra("codigoCur", codigoCur);
                startActivity(intent);
                finish();
            }
        });
    }

}
