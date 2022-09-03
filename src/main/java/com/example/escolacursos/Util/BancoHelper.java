package com.example.escolacursos.Util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BancoHelper extends SQLiteOpenHelper {

    public BancoHelper(Context context){
        super(context, "banco.db", null, 4);
    };

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("SQLITE", "CREATED!");
        db.execSQL("CREATE TABLE IF NOT EXISTS `professor`(`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `_nome` text NOT NULL, `_formacao` text NOT NULL, `_preco` text NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `curso`(`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `_tipo` text NOT NULL, `_horario` text NOT NULL, `_dia` text NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `aluno`(`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `_nome` text NOT NULL, `_idade` text NOT NULL, `_objetivo` text NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `aula`" +
                "(`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`_idProfessor` INTEGER NOT NULL, " +
                "`_idAluno` INTEGER NOT NULL, " +
                "`_idCurso` INTEGER NOT NULL, " +
                "`_observacao` text NOT NULL," +
                "FOREIGN KEY (`_idProfessor`) REFERENCES `professor`(`_id`) ON DELETE CASCADE," +
                "FOREIGN KEY (`_idAluno`) REFERENCES `aluno`(`_id`) ON DELETE CASCADE,"+
                "FOREIGN KEY (`_idCurso`) REFERENCES `curso`(`_id`) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuario`(`_usuid` INTEGER PRIMARY KEY AUTOINCREMENT, `_nome` TEXT NOT NULL, `_login` TEXT NOT NULL, `_senha` TEXT NOT NULL, `_tipo` TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLITE", "UPGRADED!");
        db.execSQL("DROP TABLE IF EXISTS `usuario`");
        db.execSQL("DROP TABLE IF EXISTS `aula`");
        db.execSQL("DROP TABLE IF EXISTS `aluno`");
        db.execSQL("DROP TABLE IF EXISTS `curso`");
        db.execSQL("DROP TABLE IF EXISTS `professor`");
        onCreate(db);
    }
}
