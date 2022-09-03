package com.example.escolacursos.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escolacursos.Util.BancoHelper;

public class CursoControle {
        private SQLiteDatabase db;
        private BancoHelper banco;

        public CursoControle(Context context) {
            banco = new BancoHelper(context);
            db = banco.getWritableDatabase();
            //banco.onUpgrade(db, 2, 3);
            //banco.onCreate(db);
        }

        public String inserirCurso(String tipo, String horario, String dia) {
            ContentValues valores;
            long resultado;

            db = banco.getWritableDatabase();
            valores = new ContentValues();
            valores.put("_tipo", tipo);
            valores.put("_horario", horario);
            valores.put("_dia", dia);


            resultado = db.insert("curso", null, valores);
            db.close();

            if (resultado == -1) return "Erro ao inserir registro";
            else return "Registro Inserido com sucesso";
        }

        public Cursor carregarCurso() {
            Cursor cursor;
            String[] campos = {"_id", "_tipo", "_horario","_dia"};
            db = banco.getReadableDatabase();
            cursor = db.query("curso", campos, null, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

        public Cursor carregarCursoPorID(int id) {
            Cursor cursor;
            String[] campos = {"_id", "_tipo", "_horario","_dia"};
            String where = "_id=" + id;
            db = banco.getReadableDatabase();
            cursor = db.query("curso", campos, where, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

    public Cursor carregarCursoPorNumero(String tipo){
        Cursor cursor;
        String[] campos =  {"_id", "_tipo", "_horario", "_dia"};
        String where = "_tipo LIKE ?";
        String[] whereArgs = new String[] { "%" + tipo + "%"};
        db = banco.getReadableDatabase();
        cursor = db.query("curso" ,campos, where, whereArgs, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

        public void alterarCurso(int id, String tipo, String horario, String dia) {
            ContentValues valores;
            String where;

            db = banco.getWritableDatabase();

            where = "_id=" + id;

            valores = new ContentValues();
            valores.put("_tipo", tipo);
            valores.put("_horario", horario);
            valores.put("_dia", dia);


            db.update("curso", valores, where, null);
            db.close();
        }

        public void deletarCurso(int id) {
            String where = "_id=" + id;

            db = banco.getReadableDatabase();
            db.delete("curso", where, null);

            db.close();
        }
    }


