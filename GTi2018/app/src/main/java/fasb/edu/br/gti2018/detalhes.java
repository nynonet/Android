package fasb.edu.br.gti2018;

import android.app.AlertDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class detalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        ListView listaXML = (ListView) findViewById(R.id.disciplina) ;

        Bundle dados = getIntent().getExtras();
        List<Disciplina> lista = new ArrayList<>();

        if (dados != null){
            lista = (List<Disciplina>) dados.getSerializable("lista");
            ArrayAdapter<Disciplina> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, lista);
            listaXML.setAdapter( adapter );

            Log.i("Haha", String.valueOf(dados.getParcelable("aa")))    ;
        }

    }
}
