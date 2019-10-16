package edu.fasb.myestudos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {

    private static final String nDATABASE = "banco.db";
    private static final int VERSAO = 1;

    public dbHelper(Context context) {
        super(context, nDATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Cidades.getCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( Cidades.getDrop() );
    }
}
