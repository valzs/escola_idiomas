package com.example.escolacursos.Telas.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

        import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.Login;

public class InserirUsuario extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_usuarios);
        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.editText);
                EditText login = (EditText)findViewById(R.id.editText2);
                EditText senha = (EditText)findViewById(R.id.editText3);
                EditText tipo = (EditText)findViewById(R.id.editText8);

                String nomeString = nome.getText().toString();
                String loginString = login.getText().toString();
                String senhaString = senha.getText().toString();
                String tipoString = tipo.getText().toString();
                String resultado;

                if(nomeString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Você precisa preencher o campo 'Nome'!", Toast.LENGTH_LONG).show();
                    return;
                } else if(loginString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Você precisa preencher o campo 'Login'!", Toast.LENGTH_LONG).show();
                    return;
                } else if(senhaString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Você precisa preencher o campo 'Senha'!", Toast.LENGTH_LONG).show();
                    return;
                } else if(tipoString.equals("")) {
                    Toast.makeText(getApplicationContext(), "Você precisa preencher o campo 'Tipo'!", Toast.LENGTH_LONG).show();
                    return;
                } else if(senhaString.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Sua senha deve conter no mínimo 6 caracteres.", Toast.LENGTH_LONG).show();
                    return;
                }

                resultado = Login.usuCont.inserirUsuario(nomeString, loginString, senhaString, tipoString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(InserirUsuario.this, Login.class);
                startActivity(myIntent);


            }
        });
    }
}