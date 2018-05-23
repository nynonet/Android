package fasb.edu.br.filmes;

import android.graphics.Bitmap;

import java.io.Serializable;
//TODO(1) CRIAR A CLASSE JAVA QUE IRÁ RECEBER OS DADOS LÁ DA API.
//TODO(2) DEVE-SE CRIAR UM KEYAPI LÁ NO SITE PARA USAR A LICENÇA GRATUITA.
//TODO(3) DEVE-SE IMPLEMTANT O SERIALIZABLE NA CLASSE
public class Filme implements Serializable {

    private String titulo;
    private String ano;
    private String genero;
    private String escritor;
    private String atores;
    private Bitmap ImagemCapa;

    //TODO(4) GERE O MÉTODO CONSTRUTOR DA CLASSE PASSANDO OS CAMPOS JÁ DEFINIDOS...
    public Filme(String titulo, String ano, String genero, String escritor, String atores, Bitmap imagemCapa) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.escritor = escritor;
        this.atores = atores;
        ImagemCapa = imagemCapa;
    }
    //TODO(5) GERE OS GETS E SETS
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public Bitmap getImagemCapa() {
        return ImagemCapa;
    }

    public void setImagemCapa(Bitmap imagemCapa) {
        ImagemCapa = imagemCapa;
    }
    //TODO(6) REESCREVA O toString
    @Override
    public String toString() {
        return this.titulo;
    }
}
