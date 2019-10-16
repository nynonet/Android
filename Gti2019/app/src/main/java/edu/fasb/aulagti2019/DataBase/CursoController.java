package edu.fasb.aulagti2019.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
        return "";
    }

    /**
     * Retorna a lista de Cursos
     * @param filtro filtro a ser aplicado caso queira
     * @return retorna a lista de cursos.
     */
    public List<Curso> SelectCurso(String filtro) {
        //abrir o banco de dados em modo de leitura
        this.db = conexaoDB.getReadableDatabase();

        this.db.close();
        return null;
    }



}
