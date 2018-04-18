package fasb.edu.br.contatos;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MeuAdapter extends BaseAdapter {

    private List<Contato> lista;
    private Activity activity;

    public MeuAdapter(List<Contato> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.contatolayout, parent, false);

        ImageView foto = (ImageView) view.findViewById(R.id.foto);
        TextView id = (TextView) view.findViewById(R.id.lb_id);
        TextView nome = (TextView) view.findViewById(R.id.lb_nome);
        TextView fone = (TextView) view.findViewById(R.id.lb_fone);

        id.setText( String.valueOf(lista.get(position).getId()) );
        nome.setText( lista.get(position).getNome() );
        fone.setText( lista.get(position).getTelefone() );

        switch (lista.get(position).getFoto()) {
            case "A": foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.cmais) );
                break;
            case "B": foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.delphi) );
                break;
            case "C": foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.java) );
                break;
            case "D": foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.python) );
                break;
            case "E": foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.swift) );
                break;
            default: ;
        }

        return view;
    }
}
