package br.edu.fasb.blueunifasb;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

//TODO 15 - Codificando o BaseAdapter para apresentação personalizada
public class AdapterDevices extends BaseAdapter {

    Activity activity;
    List<BluetoothDevice> lista;

    @Override
    public int getCount() {
        return lista.size(); //tamanho da lista
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position); //dispositivo selecionado
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Parametros parametros;

        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.activity_devices,
                    parent,false);
            parametros = new Parametros( convertView );
            convertView.setTag( parametros );
        } else {
            parametros = (Parametros) convertView.getTag();
        }

        //Paramos aqui! :-))

        return convertView;
    }

//    TODO 16 - Criando uma classe interna de controle
    private static class Parametros {
        TextView nome;
        TextView mac;
        Button conectar;
        public Parametros(View view) {
            nome = (TextView) view.findViewById(R.id.lb_device_nome);
            mac = (TextView) view.findViewById(R.id.lb_device_mac);
            conectar = (Button) view.findViewById(R.id.btn_device_conectar);
        }
    }
}
