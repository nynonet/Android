package edu.fasb.aulagti2019.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.fasb.aulagti2019.lista_person.Curso;

public class CursoController {

    private SQLiteDatabase db;
    private ConexaoDB conexaoDB;

    /**
     * Construtor da classe passando o Contexto
     * que está executando o mesmo.
     * @param context
     */
    public CursoController(Context context){
        conexaoDB = new ConexaoDB(context);
    }

    /**
     * Comando para inserção na tabela de Cursos
     * @param curso curso que será inserido
     * @return mensagem p/o usuário
     */
    public String Insert(Curso curso) {
        //abrir o banco de dados em modo de escrita
        this.db = conexaoDB.getWritableDatabase();

        //parâmetros com os valores p/inserir
        ContentValues valores = new ContentValues();
                //  Coluna,  Valor
        valores.put("texto", curso.getTexto());
        valores.put("icone", curso.getIcone());

        //Comando para insersão dos dados e tratamento de retorno
        long retorno = this.db.insert( Curso.getTable(),
                null, valores );

        //monsta no console o valor obtido por retorno
        System.out.println("Valor do Retorno é: "+retorno);

        Log.i("EU", "Retorno foi: "+retorno);

        //fecha a conexão com o banco de dados
        this.db.close();

        //retorna a mensagem tratada p/o usuário
        return ( (retorno==-1) ? "Erro no Inserir Curso":
                "Curso inserido com Sucesso!" );

    }

    /**
     * Atualizando os dados do curso
     * @param curso curso a atualizar no banco de dados
     * @return retorna mensagem ao usuário.
     */
    public String Update(Curso curso) {
        this.db = conexaoDB.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("texto", curso.getTexto());
        valores.put("icone", curso.getIcone());

        String where = "id = "+curso.getId();
        Log.i("EU", "Condição: "+where);

        int resultado = this.db.update(Curso.getTable(),
                valores, where, null);

        String msg = (resultado==0?"Falha no atualizar"
                :"Atualizado com sucesso!");
        this.db.close();
        return msg;
    }

    /**
     * Retorna a lista de Cursos
     * @param filtro filtro a ser aplicado caso queira
     * @return retorna a lista de cursos.
     */
    public List<Curso> SelectCurso(String filtro) {
        //abrir o banco de dados em modo de leitura
        this.db = conexaoDB.getReadableDatabase();
        Cursor query = this.db.rawQuery( Curso.getSelect(filtro),
                    null );

//        Cursor query = this.db.query(Curso.getTable(), new String[]{"id", "texto", "icone"},
//                null, null, null, null, null);

        //variavel de retorno da consulta SQL com os objetos Cursos
        List<Curso> resultado = new ArrayList<>();

//        Log.i("EU", "hum?");
//        Log.i("EU", Curso.getSelect(""));


//        query.moveToFirst();    //move para o primeiro registro
        while (query.moveToNext()){
            //enquanto não chegar ao final da lista vai movendo para
//            o próximo.

            Curso c = new Curso();

//            Log.i("EU", "Id db: "+ query.getInt( query.getColumnIndex("id") ));

            c.setId( query.getInt( query.getColumnIndex("id") ) );
            c.setTexto( query.getString( query.getColumnIndex("texto") ) );
            c.setIcone( query.getInt( query.getColumnIndex("icone") ) );
//            Log.i( "EU", "Curso Icone: "+ query.getInt( query.getColumnIndex("icone") ) );
            //adiciona o registro na lista de retorno
            resultado.add( c );

        }

        query.close();  //fecha a query

        //fecha a conexão com o banco de dados
        this.db.close();

        //retorna a lista de cursos encontrados
        return resultado;
    }



}
