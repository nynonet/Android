package edu.fasb.myestudos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

        Log.i("AFF", "ata insert: " + resultado);

        return (resultado==-1? "Erro ao inserir cidade!" : "Cidade inserida com sucesso!");
    }

    public String UpdateDados(Cidades cidades){
        long resultado;
        ContentValues valores;
        db = banco.getWritableDatabase();
        Log.i("AFF", "Hummmm ");
        valores = new ContentValues();
        valores.put( Cidades.colunas[1], cidades.getNome() );
        valores.put( Cidades.colunas[2], cidades.getUf() );

        String where = "id="+cidades.getId();
        Log.i("AFF", "where: " + where);

        resultado = db.update(Cidades.getTabela(), valores, where, null);

        db.close();
        Log.i("AFF", "ata: " + resultado);
//        Log.i("AFF", "where: " + where);
        return (resultado==0? "Erro ao atualizar cidade!" : "Cidade atualizada com sucesso!");
    }

    public List<Cidades> getSelect(String condicao){
        db = banco.getReadableDatabase();

        Cursor cursor = db.query(Cidades.getTabela(), Cidades.colunas,
                null, null, null,null, null);

        List<Cidades> retorno = new ArrayList<>();

        if (cursor != null){
            cursor.moveToFirst();

                while (cursor.moveToNext()){
                retorno.add( new Cidades(
                            cursor.getInt( cursor.getColumnIndex("id") ),
                            cursor.getString( cursor.getColumnIndex("nome") ),
                            cursor.getString( cursor.getColumnIndex("uf") )
                        )
                );
            }
        }
        cursor.close();
        db.close();

        return retorno;
    }

    public Cidades getCidade(String id){
        db = banco.getReadableDatabase();

        String where = "id = "+id;
        Cursor cursor = db.query(Cidades.getTabela(), Cidades.colunas, where,
                null, null,null, null);

        Cidades retorno = new Cidades();

        if (cursor != null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){

                retorno = new Cidades(
                                cursor.getInt( cursor.getColumnIndex("id") ),
                                cursor.getString( cursor.getColumnIndex("nome") ),
                                cursor.getString( cursor.getColumnIndex("uf") )
                        );

            }
        }
        cursor.close();
        db.close();

        return retorno;
    }


}
