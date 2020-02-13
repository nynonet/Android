package edu.fasb.myestudos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BuscaCEP extends AppCompatActivity {

    private TextView resultado;
    private EditText editCep;
    private ImageButton imageBusca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_cep);

        resultado = (TextView) findViewById(R.id.lab_cep);
        editCep = (EditText) findViewById(R.id.edit_cep);
        imageBusca = (ImageButton) findViewById(R.id.btn_cep);

        imageBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                try {
                    CEP cep = new httpCEP(editCep.getText().toString()).execute().get();
                    resultado.setText(cep.toString());
                    editCep.setText("");
                } catch (Exception e) {
                    resultado.setText("Erro!");
                }


            }
        });

    }

    private class httpCEP extends AsyncTask<Void, Void, CEP> {

        private final String cep;

        public httpCEP(String cep) {
            this.cep = cep;
        }

        @Override
        protected CEP doInBackground(Void... voids) {

            StringBuilder retorno = new StringBuilder();
            String urlEnd = "http://viacep.com.br/ws/"+this.cep+"/json/";

            Log.e("EU", "Url: "+urlEnd);

//            if ((cep!=null) && (cep.length()==8)){

                try {

                    URL url = new URL(urlEnd);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(5000);
                    connection.connect();
                    Log.e("EU", "HUMMM");
                    Scanner scanner = new Scanner(url.openStream());

                    while (scanner.hasNext()){
                        String r = scanner.next();
                        Log.e("EU", "->"+ r);
                        retorno.append(r+" ");
                    }


                } catch (Exception  e ){
                    Log.e("EU", "Olha o erro.");
                    e.printStackTrace();
                }
//            }

            return new Gson().fromJson(retorno.toString(), CEP.class);
        }
    }
}
