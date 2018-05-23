package fasb.edu.br.filmes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

//TODO (25) CLASSE PARA TRATAR SOMENTE DA BUSCA NA API
public class BuscaFilmes {

    //TODO (26) PREENCHE O PARAMETO COM IDENTIFICAÇÃP PARA O APLICATIVO "LOGS"
    private final String APPLOG = "BFILMES";

    //escrever sobre a API aqui
    //TODO (27) ENDEREÇO DA API PADRÃO
    private String site = "http://www.omdbapi.com/?";
    private URL url;
    private Activity contexto;

    private StringBuilder parametros = new StringBuilder();

    public BuscaFilmes( Activity contexto ) {
        this.contexto = contexto;
        //TODO (28) PREENCHE O PARAMETO COM SUA CHAVE KEY
        parametros.append("apikey="+ contexto.getString(R.string.OMDbAPIKey).toString() );
    }

    public List<Filme> getFilmes( String filtro ) {
        //TODO (28) PREENCHE O PARAMETO COM O FILTRO DESEJADO
        parametros.append( "&s="+ URLEncoder.encode(filtro));

        //TODO (29) CRIA A URL DE BUSCA.
        String site = this.site + parametros.toString();

        try {
            //TODO (30) PREPARA-SE PARA A BUSCA
            url = new URL(site);
            Log.i(APPLOG, "URL: "+site);
            StringBuffer textoHTML = new StringBuffer();

            try {
                //TODO (31) REALIZA O TRATAMENTO DE BUSCA
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.connect();

                InputStream dados = http.getInputStream();

                if (dados == null) {
                    //TODO (32) CASO VENHA NULO A REQUISIÇÃO ABORTA.
                    return new ArrayList<>();
                }

                //TODO (33) PREPARA-SE PARA REALIZAR A LEITURA DA PÁGINA
                BufferedReader reader = new BufferedReader(new InputStreamReader(dados));
                String linha;


                //TODO (34) LENDO A PÁGINA HTML
                while ((linha = reader.readLine()) != null){
                    textoHTML.append(linha);
                }

                if (textoHTML.length() == 0){
                    //TODO (35) RETORNA VAZIO CASO NÃO ENCONTRE NADA.
                    return new ArrayList<>();
                }

                if (http != null){
                    http.disconnect();//Desconectando da página...
                }

                if (reader != null){
                    reader.close();  //fechando arquivo de leitura.
                }

            } catch (IOException e) {
                Log.e(APPLOG, e.getMessage());
                Toast.makeText(this.contexto, "Tivemos na requisição HTML", Toast.LENGTH_SHORT).show();
                return new ArrayList<>();
            }

            List<Filme> resultado = new ArrayList<>();

            try {
                //TODO (36) TRABALHANDO COM JSON CONVERANDO O ARQUIVO TEXTO PARA JSON
                JSONObject json = new JSONObject(textoHTML.toString());
//

                Log.i(APPLOG, "Json recebido: " + json.toString());

                //pegando o resultado da pesquisa para varrer os registros.
                JSONArray lista = json.getJSONArray("Search");

                for ( int i = 0; i<lista.length(); i++ ) {
                    JSONObject object = lista.getJSONObject(i);
                    Filme f = new Filme(
                            object.getString("Title"),
                            object.getString("Year"),
                            object.getString("Type"),
                            object.getString("imdbID"),
                            null,
                            getImagem( object.getString("Poster") ));

                    resultado.add(f);

                }

            } catch (JSONException e) {
                Log.e(APPLOG, e.getMessage());
            }

            return resultado;

        } catch (MalformedURLException e) {
            Log.e(APPLOG, e.getMessage());
            //Toast.makeText(this.contexto, "Tivemos um erro na busca!", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }


    }

    private Bitmap getImagem(String urlImg) {
        // TODO (37) REALIZANDO O DOWNLOAD DA IMAGEM QUE VEM NO JSON

        if (urlImg.length() == 0) {
            return null;
        }

        try {
            URL url = new URL(urlImg);
            HttpURLConnection httpImg = (HttpURLConnection) url.openConnection();
            httpImg.setDoInput(true);
            httpImg.connect();
            InputStream inputStream = httpImg.getInputStream();
            Bitmap imagem = BitmapFactory.decodeStream(inputStream);
            return imagem;
        } catch (Exception e) {
            Log.e(APPLOG, "Erro down poster: "+ e.getMessage());
//            Toast.makeText(this.contexto, "Erro no download do Poster", Toast.LENGTH_SHORT).show();
        }

        return null;
    }
}
