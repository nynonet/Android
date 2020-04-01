package br.edu.fasb.sersoresdocelular;

import android.app.Activity;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterSensorLista extends BaseAdapter {

    private List<Sensor> lista;
    private Activity activityPai;

    public AdapterSensorLista(List<Sensor> lista, Activity activityPai) {
        this.lista = lista;
        this.activityPai = activityPai;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get( position );
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Parametros dados;

        LayoutInflater layoutInflater = (LayoutInflater) activityPai.getSystemService(activityPai.LAYOUT_INFLATER_SERVICE);

        if (convertView == null ) {
            convertView = layoutInflater.inflate(R.layout.adaptersensorlist, parent, false);
            dados = new Parametros( convertView );
            convertView.setTag( dados );
        } else {
            dados = (Parametros) convertView.getTag();
        }

        dados.nome.setText( lista.get(position).getName() );
        dados.stringType.setText( lista.get(position).getStringType() );
        dados.vendor.setText( lista.get(position).getVendor() );

        return convertView;
    }


    private class Parametros {
        TextView nome;
        TextView stringType;
        TextView vendor;
        public Parametros( View view ) {
            this.nome = (TextView) view.findViewById(R.id.adapter_nome_sensor);
            this.stringType = (TextView) view.findViewById(R.id.adapter_nome_stringtype);
            this.vendor = (TextView) view.findViewById(R.id.adapter_nome_vendor);
        }
    }

}
