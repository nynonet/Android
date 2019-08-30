package edu.fasb.myestudos;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterMinhaLista extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<MinhaLista> lista;

    public AdapterMinhaLista(Context context,  List<MinhaLista> lista) {
        this.lista = lista;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get( position ).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        apoioItem obj;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.mylist, null);
            obj = new apoioItem();
            obj.img = (ImageView) convertView.findViewById(R.id.icone_a);
            obj.texto = (TextView) convertView.findViewById(R.id.nome_a);

            convertView.setTag(obj);
        } else {
            obj = (apoioItem) convertView.getTag();
        }

        MinhaLista l = lista.get(position);

        obj.texto.setText( l.getNome() );
        obj.img.setImageDrawable( l.getIcone() );

        return convertView;
    }


    private class apoioItem {
        ImageView img;
        TextView texto;
    }
}
