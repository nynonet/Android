package br.edu.fasb.blueunifasb;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;

//TODO 15 - Codificando o BaseAdapter para apresentação personalizada
public class AdapterDevices extends BaseAdapter {

    Activity activity;
    List<BluetoothDevice> lista;

    //método construtor da classe
    public AdapterDevices(Activity activity, List<BluetoothDevice> lista) {
        this.activity = activity;
        this.lista = lista;
    }

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
    public View getView(final int position, View convertView, final ViewGroup parent) {
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

        //Atribuindo os valores
        parametros.nome.setText( lista.get(position).getName() );
        parametros.mac.setText( lista.get(position).getAddress() );
        parametros.conectar.setTag( lista.get(position) );
        //Define a função para chamar no click longo
        parametros.conectar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //TODO 18 - Chamando a tela de comandos
//                Toast.makeText(v.getContext(), "oks", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( parent.getContext(), ActivityComando.class );
                intent.putExtra("device", lista.get(position));
                parent.getContext().startActivity( intent );
                return true;
            }
        });

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
