package com.example.escolacursos.Telas.Aula;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.escolacursos.R;
import com.example.escolacursos.Telas.Acesso.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class AlterarAula extends Activity {

    EditText observacao;
    public Spinner spinnerProfessor, spinnerAluno, spinnerCurso;
    ArrayAdapter<String> adapter, adapterPro, adapterCur;
    ArrayList<String> professor, aluno, curso;
    HashMap<String, Integer> dados, dadosAlu, dadosCur;
    Button alterar;
    Button deletar;
    String codigo, codigoPro, codigoAlu, codigoCur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atualizar_aula);

        codigo = this.getIntent().getStringExtra("codigo");
        codigoPro = this.getIntent().getStringExtra("codigoPro");
        codigoAlu = this.getIntent().getStringExtra("codigoAlu");
        codigoCur = this.getIntent().getStringExtra("codigoCur");

        observacao = (EditText) findViewById(R.id.editText3);

        spinnerProfessor = findViewById(R.id.professorSpinner);
        spinnerAluno = findViewById(R.id.alunoSpinner);
        spinnerCurso = findViewById(R.id.cursoSpinner);

        dados = new HashMap<>();
        dadosAlu = new HashMap<>();
        dadosCur = new HashMap<>();
        professor = new ArrayList<>();
        aluno = new ArrayList<>();
        curso = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, professor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfessor.setAdapter(adapter);

        adapterPro = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aluno);
        adapterPro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAluno.setAdapter(adapterPro);

        adapterCur= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, curso);
        adapterCur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurso.setAdapter(adapterCur);

        Cursor cursor = MainActivity.proCont.carregarProfessor();
        Log.i("SQLITE", String.valueOf(cursor.getColumnCount()));
        if(cursor.isFirst()) {
            int id = cursor.getInt(0);
            int idAux = id;
            int pos = 0;
            String professor = id + " - " + cursor.getString(1);

            while (idAux != Integer.parseInt(codigoPro)){
                if (idAux < Integer.parseInt(codigoPro)) {
                    idAux++;
                    pos++;
                    spinnerProfessor.setSelection(pos);
                }
            }
            dados.put(professor, id);
            adapter.add(professor);
        }
        adapter.notifyDataSetChanged();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String professor = id + " - " + cursor.getString(1);
            dados.put(professor, id);
            adapter.add(professor);
        }
        adapter.notifyDataSetChanged();

        Cursor cursorAlu = MainActivity.aluCont.carregarAluno();
        Log.i("SQLITE", String.valueOf(cursorAlu.getColumnCount()));
        if(cursorAlu.isFirst()) {
            int id = cursorAlu.getInt(0);
            int idAux = id;
            String aluno = id + " - " + cursorAlu.getString(1);

            int pos = 0;

            while (idAux != Integer.parseInt(codigoAlu)){
                if(idAux < Integer.parseInt(codigoAlu)) {
                    idAux++;
                    pos++;
                    spinnerAluno.setSelection(pos);
                }
            }
            dadosAlu.put(aluno, id);
            adapterPro.add(aluno);
        }
        adapterPro.notifyDataSetChanged();
        while (cursorAlu.moveToNext()) {
            int id = cursorAlu.getInt(0);
            String aluno = id + " - " + cursorAlu.getString(1);
            dadosAlu.put(aluno, id);
            adapterPro.add(aluno);

        }
        adapterPro.notifyDataSetChanged();

        Cursor cursorCur = MainActivity.curCont.carregarCurso();
        Log.i("SQLITE", String.valueOf(cursorCur.getColumnCount()));
        if(cursorCur.isFirst()) {
            int id = cursorCur.getInt(0);
            int idAux = id;
            String curso = id + " - " + cursorCur.getString(1);

            int pos = 0;

            while (idAux != Integer.parseInt(codigoCur)){
                if(idAux < Integer.parseInt(codigoCur)) {
                    idAux++;
                    pos++;
                    spinnerCurso.setSelection(pos);
                }
            }
            dadosCur.put(curso, id);
            adapterCur.add(curso);
        }
        adapterCur.notifyDataSetChanged();
        while (cursorCur.moveToNext()) {
            int id = cursorCur.getInt(0);
            String curso = id + " - " + cursorCur.getString(1);
            dadosCur.put(curso, id);
            adapterCur.add(curso);

        }
        adapterCur.notifyDataSetChanged();

        alterar = (Button) findViewById(R.id.button);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinnerProfessor.getSelectedItem() == null) {
                    Toast.makeText(getApplicationContext(), "Você precisa selecionar um professor!", Toast.LENGTH_LONG).show();
                    return;
                }
                String professor = spinnerProfessor.getSelectedItem().toString();

                if(professor.length() == 0 || !dados.containsKey(professor)) {
                    Toast.makeText(getApplicationContext(), "Você precisa selecionar um professor!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(spinnerAluno.getSelectedItem() == null) {
                    Toast.makeText(getApplicationContext(), "Você precisa selecionar uma aluno!", Toast.LENGTH_LONG).show();
                    return;
                }

                String aluno = spinnerAluno.getSelectedItem().toString();

                if(aluno.length() == 0 || !dadosAlu.containsKey(aluno)) {
                    Toast.makeText(getApplicationContext(), "Você precisa selecionar um aluno!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(spinnerCurso.getSelectedItem() == null) {
                    Toast.makeText(getApplicationContext(), "Você precisa selecionar um curso!", Toast.LENGTH_LONG).show();
                    return;
                }

                String curso = spinnerCurso.getSelectedItem().toString();

                if(curso.length() == 0 || !dadosCur.containsKey(curso)) {
                    Toast.makeText(getApplicationContext(), "Você precisa selecionar um curso!", Toast.LENGTH_LONG).show();
                    return;
                }

                MainActivity.aulCont.alterarAula(Integer.parseInt(codigo), dados.get(professor), dadosAlu.get(aluno), dadosCur.get(curso), observacao.getText().toString());
                Intent intent = new Intent(AlterarAula.this, ConsultarAula.class);
                startActivity(intent);
                finish();
            }
        });

        cursor = MainActivity.aulCont.carregarAulaPorID(Integer.parseInt(codigo));
        observacao.setText(cursor.getString(cursor.getColumnIndexOrThrow("_observacao")));

        deletar = (Button) findViewById(R.id.button14);

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.aulCont.deletarAula(Integer.parseInt(codigo));
                Intent intent = new Intent (AlterarAula.this, ConsultarAula.class);
                startActivity(intent);
                finish();
            }
        });
    }
    
}
