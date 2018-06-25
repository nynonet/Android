package fasb.edu.br.crudcliente.modal;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe Model Cliente
 */
public class Cliente implements Serializable {

    private int id;
    private String nome;
    private String uf;
    private boolean status;

    public Cliente(int id, String nome, String uf, boolean status) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.status = status;
    }

    public Cliente() {

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nome + " - " + uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
