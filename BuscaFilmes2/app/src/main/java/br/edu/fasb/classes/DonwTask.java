package br.edu.fasb.classes;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.edu.fasb.buscafilmes.R;

public class DonwTask extends AsyncTask< String, Integer, Object> {

    public DonwTaskResponse delagar = null;

    Dialog progresso;
    Context context;
    EnumRetorno retorno;

    Bitmap imagem;
    JSONObject jsonObject;

    public DonwTask(Context context, EnumRetorno enumRetorno, DonwTaskResponse delagado ) {
        this.context = context;
        this.retorno = enumRetorno;
        this.delagar = delagado;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progresso = new Dialog( this.context );
        progresso.setContentView(R.layout.dialog_msg);
        progresso.setTitle("Busca Filmes");
        progresso.show();
    }

    @Override
    protected Object doInBackground(String... parametro) {
        try {
            System.out.println( parametro[0] );
            URL url = new URL(parametro[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (this.retorno == EnumRetorno.BITMAP) {
                connection.setDoInput(true);
            }
            connection.connect();

            InputStream dados = connection.getInputStream();

            if (this.retorno == EnumRetorno.BITMAP) {
                //caso for imagem use esse comando
                this.imagem = BitmapFactory.decodeStream(dados);
            } else  {
                //caso seja JSON use esse outro comando
                StringBuilder textoHTML = new StringBuilder();
                if (dados == null) {
                } else {
                    BufferedReader reader = new BufferedReader (new InputStreamReader(dados));

                    String linha;
                    while ( (linha = reader.readLine()) != null ) {
                        textoHTML.append( linha );
                    }

                    reader.close();
                }
                jsonObject = new JSONObject( textoHTML.toString() );
            }

            //tratar os dados obtidos.
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ( (this.retorno == EnumRetorno.JSON) ? jsonObject : imagem );
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progresso.dismiss();

        delagar.AtualizarTela( o ); //delegando para outro processar o resultado.
    }
}
