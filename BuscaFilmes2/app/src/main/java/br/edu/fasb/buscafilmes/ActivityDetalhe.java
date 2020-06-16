package br.edu.fasb.buscafilmes;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import br.edu.fasb.classes.DonwTask;
import br.edu.fasb.classes.DonwTaskResponse;
import br.edu.fasb.classes.EnumRetorno;

public class ActivityDetalhe extends AppCompatActivity implements DonwTaskResponse {

    private TextView det_titulo;
    private TextView det_diretor;
    private TextView det_gerero;
    private TextView det_ano;
    private ImageView det_capa;
    private ListView det_elenco;
    private ListView det_observacao;
    private Button det_botao;
    private String idFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        det_ano = (TextView) findViewById(R.id.detalhe_ano);
        det_titulo = (TextView) findViewById(R.id.detalhe_titulo);
        det_gerero = (TextView) findViewById(R.id.detalhe_genero);
        det_diretor = (TextView) findViewById(R.id.detalhe_diretor);

        det_capa = (ImageView) findViewById(R.id.detalhe_capa);
        det_elenco = (ListView) findViewById(R.id.detalhe_elenco);
        det_observacao = (ListView) findViewById(R.id.detalhe_obs);

        det_botao = (Button) findViewById(R.id.btnVoltar);

        det_botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle parametros = getIntent().getExtras();

        if (parametros != null) {
            idFilme = parametros.getString("imdbID");
            BuscarFilme();
        }

    }

    private void BuscarFilme() {
        String site = "https://www.omdbapi.com/?apikey="+ getString(R.string.app_key_omdbapi) +"&i="+ idFilme;
        new DonwTask(this, EnumRetorno.JSON, this).execute( site );
    }

    private void CarregaElenco(String lista) {
        String[] elenco = lista.split(",");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, elenco);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                det_elenco.setAdapter(adapter);
            }
        });

    }

    private void CarregaObs(String lista) {
        String[] obs = lista.split(";");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, obs);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                det_observacao.setAdapter(adapter);
            }
        });

    }
    private void CarregaCapa(final String link){
        if ( link.trim().length() == 0 || link.equals("N/A") ) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    det_capa.setImageResource( R.drawable.ic_launcher_foreground );
                }
            });
        } else {
            //download
            new DonwTask(this, EnumRetorno.BITMAP, this).execute(link);
        }
    }

    @Override
    public void AtualizarTela(Object o) {

        try {
            JSONObject jsonObject = (JSONObject) o;
            if (jsonObject.getBoolean("Response")) {
                //obteve resposta OK
                final String ano = jsonObject.getString("Year");
                final String titulo = jsonObject.getString("Title");
                final String genero = jsonObject.getString("Genre");
                final String diretor = jsonObject.getString("Director");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        det_ano.setText( ano );
                        det_titulo.setText( titulo );
                        det_gerero.setText( genero );
                        det_diretor.setText( diretor );
                    }
                });

                CarregaCapa( jsonObject.getString("Poster") );
                CarregaElenco( jsonObject.getString("Actors") );
                StringBuilder osb = new StringBuilder();
                osb.append("Duração: " + jsonObject.getString("Runtime") + ";" );
                osb.append("Pais: " + jsonObject.getString("Country") + ";" );
                osb.append("Linguagem: " + jsonObject.getString("Language") );

                CarregaObs( osb.toString() );
            }
        } catch (Exception e) {
            try {
                Bitmap imagem = (Bitmap) o;
                det_capa.setImageBitmap( imagem );
            } catch (Exception e1) {
                System.out.println("Objeto retornado não é um JSON ou BITMAP valido!");
            }
        }


    }
}



