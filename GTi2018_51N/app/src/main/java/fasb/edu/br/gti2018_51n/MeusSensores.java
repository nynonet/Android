package fasb.edu.br.gti2018_51n;

import android.app.Activity;
import android.hardware.Sensor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MeusSensores extends BaseAdapter {

    List<Sensor> lista;
    Activity activity;

    public MeusSensores(List<Sensor> lista, Activity activity) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater()
                .inflate(R.layout.my_sensores, parent, false);
        TextView nome = (TextView) view.findViewById(R.id.sNome);
        TextView tipo = (TextView) view.findViewById(R.id.sType);

        nome.setText( lista.get(position).getName() );
        tipo.setText( lista.get(position).getStringType() );

        return view;
    }
}
