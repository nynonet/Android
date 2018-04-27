package fasb.edu.br.contatos;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contato> lista = new ArrayList<>();
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listaView);
        button = (Button) findViewById(R.id.btnResultado);

        lista.add(  new Contato(1,"C++", "77 99999-1111", "A"));
        lista.add(  new Contato(2,"Delphi", "77 99999-2222", "B"));
        lista.add(  new Contato(3,"Java", "77 99999-3333", "C"));
        lista.add(  new Contato(4,"Python", "77 99999-4444", "D"));
        lista.add(  new Contato(5,"Swift", "77 99999-5555", "E"));
        lista.add(  new Contato(6,"Swift 2", "77 99999-6666", ""));
        lista.add(  new Contato(7,"Swift 3", "77 99999-8888", "C"));
        lista.add(  new Contato(8,"Swift 4", "77 99999-9999", "A"));
       // lista.add(  new Contato(6,"Android", "77 99999-6666", "F"));

        MeuAdapter adapter = new MeuAdapter(lista, this);

        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http://www.guj.com.br/t/percorrendo-arraylist-com-for-each/52374

                StringBuilder res = new StringBuilder();

                for (Contato c : lista){

                    if (c.isMarcado()){
                        res.append( c );
                        res.append("\n");
                    }

                }
                //Toast.makeText( MainActivity.this, res.toString(), Toast.LENGTH_SHORT ).show();

                if (res.length()==0) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Resultado das Avaliações");
                builder.setMessage(res.toString());
                builder.setPositiveButton("Ok", null);

                AlertDialog alerta = builder.create();
                alerta.show();

            }
        });
    }


}
