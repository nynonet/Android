package br.edu.fasb.buscafilmes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import br.edu.fasb.classes.DonwTask;
import br.edu.fasb.classes.DonwTaskResponse;
import br.edu.fasb.classes.EnumRetorno;
import br.edu.fasb.classes.MeusFilmes;


public class MainActivity extends AppCompatActivity implements DonwTaskResponse {
//
    private EditText edit_busca;
    private ImageButton btn_busca;
    private TextView texto_resultado;
    private ListView lista_resultado;

    private List<MeusFilmes> filmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_busca = (ImageButton) findViewById(R.id.btnBusca);
        edit_busca = (EditText) findViewById(R.id.editbusca);
        texto_resultado = (TextView) findViewById(R.id.textResultado);
        lista_resultado = (ListView) findViewById(R.id.listaResutado);

        lista_resultado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalhe = new Intent(getApplication(), ActivityDetalhe.class);
                //enviando os paramentros ou os dados
                detalhe.putExtra("imdbID", filmeList.get( position ).getImdbID() );
                startActivity( detalhe );
                return true;
            }
        });

        btn_busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_busca.getText().length() > 0) {
                    CarregaFilmes( edit_busca.getText().toString() );
                } else {
                    Toast.makeText(getApplication(), "Informe o nome de um titulo a buscar", Toast.LENGTH_LONG).show();
                    edit_busca.requestFocus();
                }
            }
        });

        UpdateTela();
    }

    final private void UpdateTela() {
        if (this.filmeList == null) {
            this.filmeList = new ArrayList<>();
        }

        final ArrayAdapter<MeusFilmes> adapter = new ArrayAdapter<MeusFilmes>( this,
                R.layout.support_simple_spinner_dropdown_item, filmeList);

        this.texto_resultado.setText( getString(R.string.app_resutado).replace("%",
                String.valueOf(filmeList.size()) ) );

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lista_resultado.setAdapter( adapter );
            }
        });
    }

    private void CarregaFilmes( String filtro ){
        this.filmeList = new ArrayList<>();
        btn_busca.setEnabled(false);
        String site = "https://www.omdbapi.com/?apikey="+ getString(R.string.app_key_omdbapi) +"&s="+
                URLEncoder.encode(filtro);
        new DonwTask( this, EnumRetorno.JSON, MainActivity.this ).execute( site );
    }

    @Override
    public void AtualizarTela(Object o) {
        btn_busca.setEnabled(true);
        try {
            JSONObject resposta = (JSONObject) o;
            if (resposta.getBoolean("Response")) {
                JSONArray jsonDados = resposta.getJSONArray("Search");
                for ( int i=0; i<jsonDados.length(); i++  ) {
                    JSONObject objFilme = jsonDados.getJSONObject(i);
                    MeusFilmes filme = new MeusFilmes();
                    filme.setTitulo( objFilme.getString("Title") );
                    filme.setAno( objFilme.getString("Year") );
                    filme.setImdbID( objFilme.getString("imdbID") );
                    filmeList.add(filme);
                }
                UpdateTela();
            } else {
                filmeList.clear();
                UpdateTela();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
