package edu.fasb.aulagti2019.lista_person;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Curso implements Parcelable {

    private int id;
    private String texto;
    private int icone;


    public Curso(int id, String texto, int icone) {
        this.id = id;
        this.texto = texto;
        this.icone = icone;
    }

    protected Curso(Parcel in) {
        id = in.readInt();
        texto = in.readString();
        icone = in.readInt();
    }

    public static final Creator<Curso> CREATOR = new Creator<Curso>() {
        @Override
        public Curso createFromParcel(Parcel in) {
            return new Curso(in);
        }

        @Override
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;  //pode deixar o valor default
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // escreve os dados que serão transportados
        dest.writeInt(this.id);
        dest.writeString(this.texto);
        dest.writeInt(this.icone);
    }

    /**
     * Retorna a SQL de SELECT do curso
     * @param condicao - paramentos p/filtro ex.: where id = 1
     * @return
     */
    public String getSelect( String condicao ) {
        return "SELECT * FROM cursos "+ condicao;
    }

    /**
     * Retorna a SQL de Criação da Tabela curso
     * @return
     */
    public static String getCreateTable() {
        return "CREATE TABLE cursos (id auto_increment, " +
                "texto varchar(60), icone integer, primary key (id) );";
    }

    /**
     * Retorna o nome da tabela
     * @return
     */
    public static String getTable () {
        return "cursos";
    }
}
