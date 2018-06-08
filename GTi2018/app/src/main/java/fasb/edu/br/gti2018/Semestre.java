package fasb.edu.br.gti2018;

import java.io.Serializable;

public class Semestre implements Serializable {
    private  int id;
    private  String nome;
//    private static final long serialVersionUID = 465434120;

    public Semestre( int id, String nome ){
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;
    }
}
