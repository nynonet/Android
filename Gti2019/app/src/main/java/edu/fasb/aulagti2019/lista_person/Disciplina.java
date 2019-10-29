package edu.fasb.aulagti2019.lista_person;

import android.support.annotation.NonNull;

public class Disciplina {

    private int id;
    private String nome;
    private Curso id_curso;

    public Disciplina(int id, String nome, Curso id_curso) {
        this.id = id;
        this.nome = nome;
        this.id_curso = id_curso;
    }

    public Disciplina() {

    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getId_curso() {
        return id_curso;
    }

    public void setId_curso(Curso id_curso) {
        this.id_curso = id_curso;
    }

    public static String getCreateTable(){
        return "CREATE TABLE disciplina (id integer not null, " +
                "id_curso integer not null," +
                "nome varchar(60), primary key (id));";
    }

    public static String getTable(){
        return "disciplina";
    }

    public static String getSelect( String condicao ) {
        return "SELECT id, nome, id_curso FROM disciplina "+ condicao;
    }


}
