package edu.fasb.aulagti2019.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.fasb.aulagti2019.lista_person.Curso;
import edu.fasb.aulagti2019.lista_person.Disciplina;

public class DisciplinaController {

    private SQLiteDatabase db;
    private ConexaoDB conexaoDB;
    private Context contextLocal;

    public DisciplinaController(Context context) {
        this.conexaoDB = new ConexaoDB(context);
        this.contextLocal = context;
    }

    public List<Disciplina> SelectDisciplina(String filtro) {
        this.db = conexaoDB.getReadableDatabase();

        Cursor query = this.db.rawQuery( Disciplina.getSelect(filtro),
                null );

        List<Disciplina> resultado = new ArrayList<>();

        while (query.moveToNext()){

            Disciplina c = new Disciplina();

            c.setId( query.getInt( query.getColumnIndex("id") ) );
            c.setNome( query.getString( query.getColumnIndex("nome") ) );

            Curso a = new Curso();

            a = new CursoController( contextLocal ).SelectCurso(   " WHERE id = " +
                    query.getString( query.getColumnIndex("id_curso") ) ).get(0);

            c.setId_curso( a );


            resultado.add( c );

        }

        query.close();  //fecha a query

        //fecha a conexão com o banco de dados
        this.db.close();

        //retorna a lista de cursos encontrados
        return resultado;
    }

}
