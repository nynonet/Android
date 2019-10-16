package edu.fasb.aulagti2019.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import edu.fasb.aulagti2019.lista_person.Curso;

/**
 * Responsável por gerenciar o Database
 */
public class ConexaoDB extends SQLiteOpenHelper {

    //nome do arquivo de banco de dados
    private static final String DATABASE = "banco.db";
    //identificação da versão do banco de dados
    private static final int VERSAO = 1;

    public ConexaoDB(Context context) {
        super(context, this.DATABASE, null, this.VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //criar a tabela no banco de dados
        db.execSQL( Curso.getCreateTable() );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //fazer atualizações conforme verão do banco de dados.
    }
}
