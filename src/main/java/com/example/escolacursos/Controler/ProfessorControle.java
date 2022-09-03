package com.example.escolacursos.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escolacursos.Util.BancoHelper;

public class ProfessorControle {

        private SQLiteDatabase db;
        private BancoHelper banco;

        public ProfessorControle(Context context) {
            banco = new BancoHelper(context);
            db = banco.getWritableDatabase();
            //banco.onUpgrade(db, 1, 2);
            //banco.onCreate(db);
        }

        public String inserirProfessor(String nome, String formacao, String preco) {
            ContentValues valores;
            long resultado;

            db = banco.getWritableDatabase();
            valores = new ContentValues();
            valores.put("_nome", nome);
            valores.put("_formacao", formacao);
            valores.put("_preco", preco);

            resultado = db.insert("professor", null, valores);
            db.close();

            if (resultado == -1) return "Erro ao inserir registro";
            else return "Registro Inserido com sucesso";
        }

        public Cursor carregarProfessor() {
            Cursor cursor;
            String[] campos = {"_id", "_nome", "_formacao", "_preco"};
            db = banco.getReadableDatabase();
            cursor = db.query("professor", campos, null, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

        public Cursor carregarProfessorPorID(int id){
            Cursor cursor;
            String[] campos =  {"_id", "_nome", "_formacao", "_preco"};
            String where = "_id=" + id;
            db = banco.getReadableDatabase();
            cursor = db.query("professor" ,campos,where, null, null, null, null, null);
            if(cursor!=null){
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

        public Cursor carregarProfessorPorNumero(String nome){
            Cursor cursor;
            String[] campos =  {"_id", "_nome", "_formacao", "_preco"};
            String where = "_nome LIKE ?";
            String[] whereArgs = new String[] { "%" + nome + "%"};
            db = banco.getReadableDatabase();
            cursor = db.query("professor" ,campos, where, whereArgs, null, null, null, null);
            if(cursor!=null){
                cursor.moveToFirst();
            }
            db.close();
            return cursor;
        }

        public void alterarProfessor(int id, String nome, String formacao, String preco){
            ContentValues valores;
            String where;

            db = banco.getWritableDatabase();

            where = "_id=" + id;

            valores = new ContentValues();
            valores.put("_nome", nome);
            valores.put("_formacao", formacao);
            valores.put("_preco", preco);

            db.update("professor",valores,where,null);
            db.close();
        }

        public void deletarProfessor(int id){
            String where = "_id=" + id;

            db = banco.getReadableDatabase();
            db.delete("professor",where,null);

            db.close();
        }
    }

