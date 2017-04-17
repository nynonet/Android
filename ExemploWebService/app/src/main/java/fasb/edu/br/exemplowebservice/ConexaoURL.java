package fasb.edu.br.exemplowebservice;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Andeson on 03/04/17.
 */

public class ConexaoURL {

    InputStream input = null;
    JSONObject jsonObject = null;
    String Json = "";

    public JSONObject GetUrlJson(String url) {

        try{

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost( url );

            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();

            input = httpEntity.getContent();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            String linha = null;

            while ( (linha = reader.readLine()) != null ) {
                sb.append(linha+"\n");
            }

            input.close();
            Json = sb.toString();

//            Log.i(String.valueOf(R.string.app_name), Json);

        } catch (Exception e) {
            Log.e(String.valueOf(R.string.app_name), "Erro BufferedReader: " + e.getMessage());
        }

        try {
            jsonObject = new JSONObject(Json);
        } catch (Exception e) {
            Log.e(String.valueOf(R.string.app_name), "Erro JsonObject: " + e.getMessage());
        }

        return jsonObject;
    }


}
