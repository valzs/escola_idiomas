package com.example.escolacursos.Telas.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Professor.ConsultarProfessorParametro;


public class BuscarUsuario extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_usuario);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText login = (EditText)findViewById(R.id.editText);
                String loginString = login.getText().toString();

                Intent intent = new Intent(BuscarUsuario.this, ConsultarUsuario.class);
                intent.putExtra("login", loginString);
                startActivity(intent);
                finish();
            }
        });
    }
}
