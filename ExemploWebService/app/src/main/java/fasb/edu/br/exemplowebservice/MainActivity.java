package fasb.edu.br.exemplowebservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fabricantes> myFab = new ArrayList<>();
    private ArrayAdapter<Fabricantes> adapter;

    private String urlSite = "http://exames.hol.es/dados.php?tabela=fabricantes";  //URL usada

    private ListView listView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lista);
        textView = (TextView) findViewById(R.id.endLink);

        textView.setText(urlSite);  //mostrando a URL que usei


        new Thread() {
            @Override
            public void run() {
                JSONObject fabDados = null;
                ConexaoURL conexaoURL = new ConexaoURL();

                fabDados = conexaoURL.GetUrlJson("http://nynoteste.esy.es/dados.php?tabela=fabricantes");
//                fabDados = conexaoURL.GetUrlJson( urlSite ); //pega o valor da variavel


                try {

                    JSONArray dados = new JSONArray(fabDados.getJSONArray("Dados").toString());

                    for (int i = 0; i < dados.length(); i++) {

                        JSONObject d = dados.getJSONObject(i);
                        if (d != null) {
                            Fabricantes f = new Fabricantes(d.getInt("id"), d.getString("nome"));
                            myFab.add(f);
                        }

                    }

                    adapter = new ArrayAdapter<Fabricantes>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, myFab);

                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(adapter);
                        }
                    });

                } catch (Exception e) {

                    e.printStackTrace();
                }


            }
        }.start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome = myFab.get(position).toString();

                Intent detalhes = new Intent(getApplication(),Detalhe.class);

                detalhes.putExtra("id_fab", myFab.get(position).getId());  //enviando o parâmetro

                Toast.makeText(getApplication(), "Fabricante: " + nome, Toast.LENGTH_SHORT).show();
                startActivity(detalhes);

            }
        });

    }
}
