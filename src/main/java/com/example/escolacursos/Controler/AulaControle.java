package com.example.escolacursos.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escolacursos.Util.BancoHelper;

public class AulaControle {

    private SQLiteDatabase db;
    private BancoHelper banco;

    public AulaControle(Context context) {
        banco = new BancoHelper(context);
        db = banco.getWritableDatabase();
        //banco.onUpgrade(db, 1, 2);
        //banco.onCreate(db);
    }

    public String inserirAula(int idProfessor, int idAluno, int idCurso, String observacao) {
        ContentValues valores;
        long resultado = 1;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("_idProfessor", idProfessor);
        valores.put("_idAluno", idAluno);
        valores.put("_idCurso", idCurso);
        valores.put("_observacao", observacao);

        db.beginTransaction();
        try {
            resultado = db.insert("aula", null, valores);
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        if (resultado == -1) return  "Erro ao inserir registro";
        else return "Registro inserido com sucesso";
    }

    public Cursor carregarAula() {
        Cursor cursor;
        String[] campos = {"_id", "_idProfessor", "_idAluno","_idCurso", "_observacao"};
        db = banco.getReadableDatabase();
        cursor = db.query("aula", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregarAulaPorID(int id){
        Cursor cursor;
        String[] campos =  {"_id", "_idProfessor", "_idAluno","_idCurso", "_observacao"};
        String where = "_id=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("aula" ,campos,where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public Cursor carregarAulaPorNumero(String observacao){
        Cursor cursor;
        String[] campos =  {"_id", "_idProfessor", "_idAluno", "_idCurso", "_observacao"};
        String where = "_observacao LIKE ?";
        String[] whereArgs = new String[] { "%" + observacao + "%"};
        db = banco.getReadableDatabase();
        cursor = db.query("aula" ,campos, where, whereArgs, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alterarAula(int id, int idProfessor, int idAluno, int idCurso, String observacao){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id=" + id;

        valores = new ContentValues();
        valores.put("_idProfessor", idProfessor);
        valores.put("_idAluno", idAluno);
        valores.put("_idCurso", idCurso);
        valores.put("_observacao", observacao);

        db.update("aula",valores,where,null);
        db.close();
    }

    public void deletarAula(int id){
        String where = "_id=" + id;

        db = banco.getReadableDatabase();
        db.delete("aula",where,null);

        db.close();
    }



}
