package com.example.escolacursos.Telas.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;
import com.example.escolacursos.Telas.Usuario.ConsultarUsuario;

public class AlterarUsuario extends Activity {

    EditText nome;
    EditText login;
    EditText senha;
    EditText tipo;
    Button alterar;
    Button deletar;
    Cursor cursor;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.atualizar_usuario);

        codigo = this.getIntent().getStringExtra("codigo");

        nome = (EditText) findViewById(R.id.editText4);
        login = (EditText) findViewById(R.id.editText5);
        senha = (EditText) findViewById(R.id.editText6);
        tipo = (EditText) findViewById(R.id.editText7);

        alterar = (Button) findViewById(R.id.button2);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.usuCont.alterarUsuario(Integer.parseInt(codigo),nome.getText().toString(), login.getText().toString(), senha.getText().toString(), tipo.getText().toString());
                Intent intent = new Intent(AlterarUsuario.this, ConsultarUsuario.class);
                startActivity(intent);
                finish();
            }
        });

        cursor = MainActivity.usuCont.carregarUsuarioPorID(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow("_nome")));
        login.setText(cursor.getString(cursor.getColumnIndexOrThrow("_login")));
        senha.setText(cursor.getString(cursor.getColumnIndexOrThrow("_senha")));
        tipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("_tipo")));

        deletar = (Button) findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.usuCont.deletarUsuario(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarUsuario.this, ConsultarUsuario.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
