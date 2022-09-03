package com.example.escolacursos.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escolacursos.Util.BancoHelper;

public class AlunoControle {
        private SQLiteDatabase db;
        private BancoHelper banco;

        public AlunoControle(Context context) {
            banco = new BancoHelper(context);
            db = banco.getWritableDatabase();
            //banco.onUpgrade(db, 2, 3);
            //banco.onCreate(db);
        }

        public String inserirAluno(String nome, String idade, String objetivo) {
            ContentValues valores;
            long resultado;

            db = banco.getWritableDatabase();
            valores = new ContentValues();
            valores.put("_nome", nome);
            valores.put("_idade", idade);
            valores.put("_objetivo", objetivo);


            resultado = db.insert("aluno", null, valores);
            db.close();

            if (resultado == -1) return "Erro ao inserir registro";
            else return "Registro Inserido com sucesso";
        }

        public Cursor carregarAluno() {
            Cursor cursor;
            String[] campos = {"_id", "_nome", "_idade","_objetivo"};
            db = banco.getReadableDatabase();
            cursor = db.query("aluno", campos, null, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

        public Cursor carregarAlunoPorID(int id) {
            Cursor cursor;
            String[] campos = {"_id", "_nome", "_idade","_objetivo"};
            String where = "_id=" + id;
            db = banco.getReadableDatabase();
            cursor = db.query("aluno", campos, where, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

    public Cursor carregarAlunoPorNumero(String nome){
        Cursor cursor;
        String[] campos =  {"_id", "_nome", "_idade", "_objetivo"};
        String where = "_nome LIKE ?";
        String[] whereArgs = new String[] { "%" + nome + "%"};
        db = banco.getReadableDatabase();
        cursor = db.query("aluno" ,campos, where, whereArgs, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

        public void alterarAluno(int id, String nome, String idade, String objetivo) {
            ContentValues valores;
            String where;

            db = banco.getWritableDatabase();

            where = "_id=" + id;

            valores = new ContentValues();
            valores.put("_nome", nome);
            valores.put("_idade", idade);
            valores.put("_objetivo", objetivo);


            db.update("aluno", valores, where, null);
            db.close();
        }

        public void deletarAluno(int id) {
            String where = "_id=" + id;

            db = banco.getReadableDatabase();
            db.delete("aluno", where, null);

            db.close();
        }
    }


