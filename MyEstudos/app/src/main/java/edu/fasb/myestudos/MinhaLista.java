package edu.fasb.myestudos;

import android.graphics.drawable.Drawable;

public class MinhaLista {

    private int id;
    private String nome;
    private Drawable icone;

    public MinhaLista(int id, String nome, Drawable icone) {
        this.nome = nome;
        this.icone = icone;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Drawable getIcone() {
        return icone;
    }

    public void setIcone(Drawable icone) {
        this.icone = icone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
