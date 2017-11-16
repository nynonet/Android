package nynonet.net.financiamentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executor;

import br.edu.fasb.CalculaEmprestimo;
import br.edu.fasb.Sistemas;
import br.edu.fasb.Tabela;

public class MainActivity extends AppCompatActivity {

    private ListView mResultado;
    private CalculaEmprestimo calc;
    private EditText Emprestimo;
    private EditText Tempo;
    private EditText Taxa;
    private Spinner TipoCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultado = (ListView) findViewById(R.id.listaDados);
        Emprestimo = (EditText) findViewById(R.id.Valor);
        Tempo = (EditText) findViewById(R.id.Parcelas);
        Taxa = (EditText) findViewById(R.id.Taxas);
        TipoCalc = (Spinner) findViewById(R.id.ListaSistema);
        calc = new CalculaEmprestimo();
    }

    public void onCalcular(View view) {

        try {
            calc.setDuracao(Integer.parseInt(Tempo.getText().toString()));
        }
        catch (Exception ex) {
            Toast.makeText(this,"Reveja o número de parcelas!", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            calc.setTaxa_juros(Double.parseDouble(Taxa.getText().toString().replace(",", ".")));
        }
        catch (Exception ex) {
            Toast.makeText(this,"Reveja o valor da taxa!", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            calc.setValor_emprestimo( Double.parseDouble( Emprestimo.getText().toString().replace(",", ".") ));}
        catch (Exception ex) {
            Toast.makeText(this,"Reveja o valor do emprestimo!", Toast.LENGTH_LONG).show();
            return;
        }

        switch (TipoCalc.getSelectedItemPosition()) {
            case 1 : calc.setSistema(Sistemas.SAC); break;
            case 2 : calc.setSistema(Sistemas.GAUSS); break;
                default:calc.setSistema(Sistemas.PRICE); break;
        }

        ListaTabelas mDados;
        mDados = new ListaTabelas(this, calc.getResultado() );
        mResultado.setAdapter(mDados);

    }
}
