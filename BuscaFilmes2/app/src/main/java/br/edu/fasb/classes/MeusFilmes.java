package br.edu.fasb.classes;

public class MeusFilmes {

    private String titulo;
    private String ano;
    private String imdbID;

    public MeusFilmes() {
    }

    public MeusFilmes(String titulo, String ano, String imdbID) {
        this.titulo = titulo;
        this.ano = ano;
        this.imdbID = imdbID;
    }

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

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    public String toString() {
        return this.titulo + " - " + this.ano;
    }
}

/**
 *{
 * *  "Title":"King Kong: The Official Game of the Movie",
 * *  "Year":"2005",
 * *   "imdbID":"tt0489105",
 * *   "Type":"game",
 * *   "Poster":"https://m.media-amazon.com/images/M/MV5BNjE0NDEwMTQ3Ml5BMl5BanBnXkFtZTcwMDg0MDkzMQ@@._V1_SX300.jpg"
 * * }
 */