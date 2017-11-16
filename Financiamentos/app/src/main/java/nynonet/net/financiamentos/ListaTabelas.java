package nynonet.net.financiamentos;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.Format;
import java.util.List;

import br.edu.fasb.Tabela;

/**
 * Created by Andeson on 21/11/16.
 */

public class ListaTabelas extends BaseAdapter {
    Context mContext;
    List<Tabela> lista;

    public ListaTabelas(Context mContext, List<Tabela> lista) {
        this.mContext = mContext;
        this.lista = lista;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.lista_dados, null);
        TextView parcela = (TextView) v.findViewById(R.id.f_parcela);
        TextView prestacao = (TextView) v.findViewById(R.id.f_prestacao);
        TextView juros = (TextView) v.findViewById(R.id.f_juros);
        TextView amortizacao = (TextView) v.findViewById(R.id.f_amortizacao);
        TextView saldo = (TextView) v.findViewById(R.id.f_saldo);

        parcela.setText(String.format("%1$03d",lista.get(position).getTempo()));
        prestacao.setText(String.format( "%1$.2f", lista.get(position).getPrestacao()));
        juros.setText(String.format( "%1$.2f", lista.get(position).getJuros()));
        amortizacao.setText(String.format( "%1$.2f", lista.get(position).getAmortizacao()));
        saldo.setText(String.format( "%1$.2f",lista.get(position).getSaldoDevedor()));

        v.setId( lista.get(position).getTempo() );

        return v;
    }
}
