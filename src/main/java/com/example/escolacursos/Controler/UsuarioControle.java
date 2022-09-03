package com.example.escolacursos.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escolacursos.Util.BancoHelper;

public class UsuarioControle {
    private SQLiteDatabase db;
    private BancoHelper banco;


    public UsuarioControle(Context context) {
        banco = new BancoHelper(context);
        db = banco.getWritableDatabase();
        //banco.onUpgrade(db, 1, 2);
        //banco.onCreate(db);
    }

    public String inserirUsuario(String nome, String login, String senha, String tipo) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("_nome", nome);
        valores.put("_login", login);
        valores.put("_senha", senha);
        valores.put("_tipo", tipo);

        resultado = db.insert("usuario", null, valores);
        db.close();

        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public long validarUsuario(String login, String senha){
        String[] campos =  {"_usuid", "_nome", "_login", "_senha", "_tipo"};
        String where = "_login= ? AND _senha= ?";
        String[] whereArgs = new String[] {login, senha};
        db = banco.getReadableDatabase();

        try {
            int i = 0;
            Cursor cursor = null;
            cursor = db.query("usuario" ,campos, where, whereArgs, null, null, null, null);
            cursor.moveToFirst();
            i = cursor.getCount();
            cursor.close();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Cursor carregarUsuario() {
        Cursor cursor;
        String[] campos = {"_usuid", "_nome", "_login", "_senha","_tipo"};
        db = banco.getReadableDatabase();
        cursor = db.query("usuario", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregarUsuarioPorID(int id){
        Cursor cursor;
        String[] campos =  {"_usuid", "_nome", "_login", "_senha","_tipo"};
        String where = "_usuid=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query("usuario" ,campos,where, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregarUsuarioPorNumero(String login){
        Cursor cursor;
        String[] campos =  {"_usuid", "_nome", "_login", "_senha","_tipo"};
        String where = "_login LIKE ?";
        String[] whereArgs = new String[] { "%" + login + "%"};
        db = banco.getReadableDatabase();
        cursor = db.query("usuario" ,campos, where, whereArgs, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alterarUsuario(int id, String nome, String login, String senha, String tipo){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_usuid=" + id;

        valores = new ContentValues();
        valores.put("_nome", nome);
        valores.put("_login", login);
        valores.put("_senha", senha);
        valores.put("_tipo", tipo);

        db.update("usuario",valores,where,null);
        db.close();
    }

    public void deletarUsuario(int id){
        String where = "_usuid=" + id;

        db = banco.getReadableDatabase();
        db.delete("usuario",where,null);

        db.close();
    }
}

