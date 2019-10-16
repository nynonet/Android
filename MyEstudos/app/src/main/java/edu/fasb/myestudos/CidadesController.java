package edu.fasb.myestudos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CidadesController {

    private SQLiteDatabase db;
    private dbHelper banco;

    public CidadesController(Context context){
        banco = new dbHelper(context);
    }

    public String InsertDados( Cidades cidades ){
        long resultado;
        ContentValues valores;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put( Cidades.colunas[1], cidades.getNome() );
        valores.put( Cidades.colunas[2], cidades.getUf() );

        resultado = db.insert(Cidades.getTabela(),null, valores);

        db.close();

        return (resultado==-1? "Erro ao inserir cidade!" : "Cidade inserida com sucesso!");
    }

    public String UpdateDados(Cidades cidades){


        return "";
    }


}
