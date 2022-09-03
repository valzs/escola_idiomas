package com.example.escolacursos.Telas.Acesso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.escolacursos.R;
import com.example.escolacursos.Controler.UsuarioControle;
import com.example.escolacursos.Telas.Usuario.InserirUsuario;

    public class Login extends AppCompatActivity {
            private EditText senhaText;
            private EditText loginText;

            public static UsuarioControle usuCont;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                if (usuCont == null) usuCont = new UsuarioControle(this);
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                loginText = (EditText) findViewById(R.id.login);
                senhaText = (EditText) findViewById(R.id.senha);

                Button login = (Button) findViewById(R.id.btentrar);

                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String loginString = loginText.getText().toString();
                        String senhaString = senhaText.getText().toString();

                        long resultado = Login.usuCont.validarUsuario(loginString, senhaString);

                        if (resultado == 0) {
                            Toast.makeText(getApplicationContext(), "Usuário não encontrado.", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            Intent myIntent = new Intent(Login.this, MainActivity.class);
                            startActivity(myIntent);
                        }

                    }
                });
            }

            public void InserirUsuario(View view) {
                Intent myIntent = new Intent(this, InserirUsuario.class);
                this.startActivity(myIntent);
            }
        }


