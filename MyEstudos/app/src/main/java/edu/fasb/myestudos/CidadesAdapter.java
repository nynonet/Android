package edu.fasb.myestudos;

import android.arch.core.executor.TaskExecutor;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CidadesAdapter extends BaseAdapter {

    private List<Cidades> lista;
    private LayoutInflater layout;

    public CidadesAdapter(Context context, List<Cidades> lista) {
        this.lista = lista;
        this.layout = LayoutInflater.from(context);
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
        return lista.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final apoio obj;

        final Cidades c = lista.get( position );

        if (convertView == null) {
            convertView = layout.inflate(R.layout.cidadeslista, null);
            obj = new apoio();
            obj.nome = (TextView) convertView.findViewById(R.id.labelNome);
            obj.editar = (ImageButton) convertView.findViewById(R.id.btnEditar);
            obj.deletar = (ImageButton) convertView.findViewById(R.id.btnDeletar);
        } else {
            obj = (apoio) convertView.getTag();
        }

        if (obj != null){
            obj.nome.setText( c.toString() );

            obj.editar.setTag( lista.get(position) );

            obj.deletar.setTag( lista.get(position) );

            obj.deletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("deu certo deletar?");
                    lista.remove(  position );
                }
            });

            obj.editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), TelaCidade.class);
                    i.putExtra("id", c.getId() );  //para novos registro
                    i.putExtra("nome", c.getNome() );  //para novos registro
                    i.putExtra("uf", c.getUf() );  //para novos registro
//                    startActivity(i);
                    v.getContext().startActivity(i);
                }
            });
        }
        return convertView;
    }


    private class apoio {
        TextView nome;
        ImageButton editar;
        ImageButton deletar;
    }
}
