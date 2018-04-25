package fasb.edu.br.contatos;

public class Contato {

    private int id;
    private String nome;
    private String telefone;
    private String foto;
    private float valor;
    private boolean marcado;

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Contato(int id, String nome, String telefone, String foto) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.foto = foto;
        this.valor = 0;
        this.marcado = false;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return this.nome + "  Avaliado em "+ valor;
    }
}
