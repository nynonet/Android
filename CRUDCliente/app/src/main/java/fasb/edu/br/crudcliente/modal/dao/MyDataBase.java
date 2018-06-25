package fasb.edu.br.crudcliente.modal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {

    private static final String NOME_DB = "myDatabase.db";
    private static final int VERSAO_DB = 1;

    /**
     * Criando nosso database
     * @param context
     */
    public MyDataBase(Context context) {
        super(context, NOME_DB, null, VERSAO_DB );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( ClienteDAO.CreateTable() ); //criando nossa tabela de clientes
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
