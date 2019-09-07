package edu.fasb.myestudos;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class MinhaLista implements Parcelable {

    private int id;
    private String nome;
    private Drawable icone;

    public MinhaLista(int id, String nome, Drawable icone) {
        this.nome = nome;
        this.icone = icone;
        this.id = id;
    }

    protected MinhaLista(Parcel in) {
        id = in.readInt();
        nome = in.readString();
    }

    public static final Creator<MinhaLista> CREATOR = new Creator<MinhaLista>() {
        @Override
        public MinhaLista createFromParcel(Parcel in) {
            return new MinhaLista(in);
        }

        @Override
        public MinhaLista[] newArray(int size) {
            return new MinhaLista[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Drawable getIcone() {
        return icone;
    }

    public void setIcone(Drawable icone) {
        this.icone = icone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
//        dest.writeValue(icone);
    }
}
