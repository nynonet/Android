package fasb.edu.br.gti2018;


import java.io.Serializable;

public class Disciplina implements Serializable {
    private int id;
    private Semestre semestre;
    private String nome;
    private float nota;

//    private static final long serialVersionUID = 46543445;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Disciplina(int id, Semestre semestre, String nome){
        this.id = id;
        this.nome = nome;
        this.semestre = semestre;
        this.nota = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return semestre + " " + nome;
    }
}
