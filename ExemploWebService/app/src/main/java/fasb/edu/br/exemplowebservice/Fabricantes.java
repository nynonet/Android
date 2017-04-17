package fasb.edu.br.exemplowebservice;

/**
 * Created by Andeson on 02/04/17.
 */

public class Fabricantes {
    private Integer id;
    private String nome;

    public Fabricantes(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return this.id + " " + this.nome;
    }
}
