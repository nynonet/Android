package edu.fasb.aulagti2019.lista_person;

import android.support.annotation.NonNull;

public class Curso {

    private int id;
    private String texto;
    private int icone;


    public Curso(int id, String texto, int icone) {
        this.id = id;
        this.texto = texto;
        this.icone = icone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    @NonNull
    @Override
    public String toString() {
        return this.texto;
    }
}
