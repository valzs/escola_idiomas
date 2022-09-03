package com.example.escolacursos.Telas.Usuario;

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
import com.example.escolacursos.Telas.Usuario.AlterarUsuario;

public class ConsultarUsuarioParametro extends Activity {

    private ListView lista;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_usuario);
        login = this.getIntent().getStringExtra("login");

        final Cursor cursor = MainActivity.usuCont.carregarUsuarioPorNumero(login);
        String[] loginCampos = new String[]{"_usuid", "_nome", "_login", "_senha","_tipo"};
        int[] idViews = new int[] { R.id.idUsuario, R.id.nomeUsuario, R.id.loginUsuario, R.id.senhaUsuario, R.id.tipoUsuario, };

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.usuario_layout, cursor, loginCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_usuid"));

                Intent intent = new Intent(ConsultarUsuarioParametro.this, AlterarUsuario.class);
                intent.putExtra("codigo", codigo);

                startActivity(intent);
                finish();
            }
        });
    }
}
