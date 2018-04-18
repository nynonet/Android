package fasb.edu.br.contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contato> lista = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listaView);

        lista.add(  new Contato(1,"C++", "77 99999-1111", "A"));
        lista.add(  new Contato(2,"Delphi", "77 99999-2222", "B"));
        lista.add(  new Contato(3,"Java", "77 99999-3333", "C"));
        lista.add(  new Contato(4,"Python", "77 99999-4444", "D"));
        lista.add(  new Contato(5,"Swift", "77 99999-5555", "E"));
       // lista.add(  new Contato(6,"Android", "77 99999-6666", "F"));

        MeuAdapter adapter = new MeuAdapter(lista, this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = lista.get(position).getNome();
                Toast.makeText(MainActivity.this, nome, Toast.LENGTH_LONG).show();
            }
        });

    }


}
