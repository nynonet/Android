package fasb.edu.br.exemplowebservice;

/**
 * Created by Andeson on 02/04/17.
 */

public class Modelos {

    private Integer id;
    private Fabricantes fabricante;
    private String descricao;

    public Modelos(Integer id, Fabricantes fabricante, String descricao) {
        this.id = id;
        this.fabricante = fabricante;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fabricantes getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricantes fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.id + " " + this.descricao;
    }
}
