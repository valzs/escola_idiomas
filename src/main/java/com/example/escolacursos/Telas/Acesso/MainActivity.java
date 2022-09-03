package com.example.escolacursos.Telas.Acesso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.escolacursos.Controler.AulaControle;
import com.example.escolacursos.Controler.CursoControle;
import com.example.escolacursos.Controler.UsuarioControle;
import com.example.escolacursos.Telas.Aluno.BuscarAluno;
import com.example.escolacursos.Telas.Aula.BuscarAula;
import com.example.escolacursos.Telas.Curso.BuscarCurso;
import com.example.escolacursos.Telas.Curso.InserirCurso;
import com.example.escolacursos.Telas.Professor.BuscarProfessor;
import com.example.escolacursos.Controler.AlunoControle;
import com.example.escolacursos.Controler.ProfessorControle;
import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Aula.InserirAula;
import com.example.escolacursos.Telas.Aluno.InserirAluno;
import com.example.escolacursos.Telas.Professor.InserirProfessor;
import com.example.escolacursos.Telas.Usuario.BuscarUsuario;
import com.example.escolacursos.Telas.Usuario.InserirUsuario;

public class MainActivity extends AppCompatActivity {

    public static AlunoControle aluCont;
    public static ProfessorControle proCont;
    public static AulaControle aulCont;
    public static UsuarioControle usuCont;
    public static CursoControle curCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(proCont == null) proCont = new ProfessorControle(this);
        if(aluCont == null) aluCont = new AlunoControle(this);
        if(aulCont == null) aulCont = new AulaControle(this);
        if(usuCont == null) usuCont = new UsuarioControle(this);
        if(curCont == null) curCont = new CursoControle(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void InserirAluno(View view) {
        Intent myIntent = new Intent(this, InserirAluno.class);
        this.startActivity(myIntent);
    }

    public void BuscarAluno(View view) {
        Intent myIntent = new Intent(this, BuscarAluno.class);
        this.startActivity(myIntent);
    }

    public void InserirAula(View view) {
        Intent myIntent = new Intent(this, InserirAula.class);
        this.startActivity(myIntent);
    }

    public void BuscarAula(View view) {
        Intent myIntent = new Intent(this, BuscarAula.class);
        this.startActivity(myIntent);
    }

    public void BuscarProfessor(View view) {
        Intent myIntent = new Intent(this, BuscarProfessor.class);
        this.startActivity(myIntent);
    }

    public void InserirProfessor(View view) {
        Intent myIntent = new Intent(this, InserirProfessor.class);
        this.startActivity(myIntent);
    }
    public void BuscarUsuario(View view) {
        Intent myIntent = new Intent(this, BuscarUsuario.class);
        this.startActivity(myIntent);
    }

    public void InserirUsuario(View view) {
        Intent myIntent = new Intent(this, InserirUsuario.class);
        this.startActivity(myIntent);
    }
    public void BuscarCurso(View view) {
        Intent myIntent = new Intent(this, BuscarCurso.class);
        this.startActivity(myIntent);
    }

    public void InserirCurso(View view) {
        Intent myIntent = new Intent(this, InserirCurso.class);
        this.startActivity(myIntent);
    }
}

