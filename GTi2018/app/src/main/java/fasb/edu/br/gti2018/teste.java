package fasb.edu.br.gti2018;

import android.os.Parcel;
import android.os.Parcelable;

public class teste implements Parcelable {

    private int id;
    private String nome;

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

    public teste(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public teste(Parcel p) {
        if (p!=null) {
            id = p.readInt();
            nome = p.readString();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
    }

    public static final Parcelable.Creator<teste>
            CREATOR = new Parcelable.Creator<teste>() {

        @Override
        public teste createFromParcel(Parcel source) {
            return new teste(source);
        }

        @Override
        public teste[] newArray(int size) {
            return new teste[size];
        }
    };

    @Override
    public String toString() {
        return nome;
    }
}
