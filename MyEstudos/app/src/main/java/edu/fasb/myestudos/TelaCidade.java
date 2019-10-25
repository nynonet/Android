package edu.fasb.myestudos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TelaCidade extends AppCompatActivity {

    private TextView titulo;
    private EditText edt_cidade;
    private Spinner edt_uf;
    private Button btnSalvar;
    private Cidades cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cidade);

        titulo = (TextView) findViewById(R.id.tela_cidade_status);
        edt_cidade = (EditText) findViewById(R.id.tela_cidade_nome);
        edt_uf  = (Spinner) findViewById(R.id.tela_cidade_uf);
        btnSalvar = (Button) findViewById(R.id.tela_cidade_salvar);

        if (getIntent().getExtras() != null) {
            Log.i("UFF", "SIM");

            cidade = new Cidades(
                    getIntent().getIntExtra("id", -1),
                    getIntent().getStringExtra("nome"),
                    getIntent().getStringExtra("uf")
            );

            edt_cidade.setText( cidade.getNome() );

            for (int i =0; i<edt_uf.getCount()-1; i++ ) {
                if (edt_uf.getItemAtPosition(i).toString().equals( cidade.getUf() )){
                    edt_uf.setSelection(i);
//                    return;
                }
            }

        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cidades cad = new Cidades();
                cad.setNome( edt_cidade.getText().toString() );
                cad.setUf( edt_uf.getSelectedItem().toString() );
                String msg;

                Log.i("AFF", "Hum? Antes...");

                if (cidade == null) {
                    Log.i("AFF", "Cidade == null ok!");
                    msg = new CidadesController(getApplication()).InsertDados(cad);
                } else {
                    cad.setId( cidade.getId() );
                    Log.i("AFF", "Cidade != null ok!");
                    msg = new CidadesController(getApplication()).UpdateDados(cad);
                    Log.i("AFF", "Cidade ?");
                }

                Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();

                finish();
            }
        });

    }
}
