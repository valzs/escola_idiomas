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
import com.example.escolacursos.Telas.Aula.AlterarAula;

public class ConsultarAulaParametro extends Activity {

    private ListView lista;
    String observacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_aula);
        observacao = this.getIntent().getStringExtra("observacao");

        final Cursor cursor = MainActivity.aulCont.carregarAulaPorNumero(observacao);
        String[] observacaoCampos = new String[]{"_id", "_idP", "_idA", "_idC", "_observacao"};
        int[] idViews = new int[] { R.id.id, R.id.idPro, R.id.idAlu, R.id.idCur, R.id.observacao};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.aula_layout, cursor, observacaoCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView3);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(ConsultarAulaParametro.this, AlterarAula.class);
                intent.putExtra("codigo", codigo);

                startActivity(intent);
                finish();
            }
        });
    }
}
