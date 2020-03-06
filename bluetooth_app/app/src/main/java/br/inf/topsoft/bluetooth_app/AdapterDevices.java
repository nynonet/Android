package br.inf.topsoft.bluetooth_app;

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

public class AdapterDevices extends BaseAdapter {

//    TODO 13 - Condificando a personalização do adapter

    private List<BluetoothDevice> dispositivos;
    private Activity activity;

    public AdapterDevices(Activity activity, List<BluetoothDevice> dispositivos) {
        this.dispositivos = dispositivos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return dispositivos.size();
    }

    @Override
    public Object getItem(int position) {
        return dispositivos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dispositivos.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final ObjetosView objetosView;

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_dispositivos, parent, false);
            objetosView = new ObjetosView(convertView);
            convertView.setTag(objetosView);
        } else  {
            objetosView = (ObjetosView) convertView.getTag();
        }

        objetosView.nome.setText( dispositivos.get(position).getName() );
        objetosView.mac.setText( dispositivos.get(position).getAddress() );
        objetosView.conectar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(parent.getContext(), ControllerDevice.class);
                i.putExtra("device", dispositivos.get(position));
                activity.startActivityForResult(i, 2);
                return true;
            }
        });

        return convertView;
    }

    private static class ObjetosView {

        TextView nome;
        TextView mac;
        Button conectar;

        public ObjetosView(View v) {

            nome = (TextView) v.findViewById(R.id.lb_dispositivo_nome);
            mac = (TextView) v.findViewById(R.id.lb_dispositivo_mac);
            conectar = (Button) v.findViewById(R.id.btn_dispositivo_conectar);

        }
    }
}
