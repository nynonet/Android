package edu.fasb.myestudos;

import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

public class Cidades {
    public static final String[] colunas = {"id", "nome", "uf"};
    private Integer id;
    private String nome;
    private String uf;

    public Cidades(Integer id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @NonNull
    @Override
    public String toString() {
        return this.uf + " - "+this.nome;
    }

    //Dados para percistencia de dados
    public String getINSERT(){
        return "INSERT INTO cidades (nome, uf) values (?,?)";
    }

    public String getUPDATE() {
        return "UPDATE cidades SET nome =?, uf =? WHERE id = ?";
    }

    public String getDELETE() {
        return "DELETE FROM cidades WHERE id = ?";
    }

    public static String getSELECT(String where) {
        return "SELECT id, nome, uf FROM cidades "+ where;
    }

    public static String getCreate(){
        return "CREATE TABLE cidades (id integer not null, nome varchar(60), uf char(2), primary key (id));";
    }
    public static String getDrop() {
        return "DROP TABLE IF EXISTS cidades";
    }

    public static String getTabela(){
        return "cidades";
    }
}
