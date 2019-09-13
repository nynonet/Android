package edu.fasb.myestudos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_act1;
    private Button btn_act2;
    private Button btn_act3;
    private String TAG_APP = "MyAPP";
    private ListView myList1;
    private AdapterMinhaLista meuAdapter;
    private ArrayList<MinhaLista> minhaListas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_act1 = (Button) findViewById(R.id.btnTela1);
        btn_act2 = (Button) findViewById(R.id.btnConfig);
        btn_act3 = (Button) findViewById(R.id.btnConfigShow);
        myList1 = (ListView) findViewById(R.id.lista1);

        btn_act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nova = new Intent(MainActivity.this, Main2Activity.class);
                nova.putExtra("Nome", "Vai da tudo certo!" );
                startActivity(nova);
                Log.i(TAG_APP, "Botão 1 Ok!");
            }
        });

        btn_act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cfg = new Intent(MainActivity.this, Configuracoes.class);
                startActivity(cfg);
                Log.i(TAG_APP, "Botão 2 Ok!");
            }
        });

        btn_act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = Configuracoes.getConfig(getApplication()).getString("edit_text_preference_1", "");
                Toast.makeText( v.getContext(), n , Toast.LENGTH_LONG ).show();
                Log.i(TAG_APP, n);
            }
        });

        myList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String nome = myList1.getItemAtPosition( position ).toString();
//                Toast.makeText( parent.getContext(), nome , Toast.LENGTH_LONG ).show();
//                Log.e(TAG_APP, nome);

                Intent nova = new Intent(MainActivity.this, Main2Activity.class);

                nova.putExtra("Obj", (MinhaLista) minhaListas.get( position ) );

                //nova.putExtra("Nome", "Vai da tudo certo!" );

                startActivity(nova);


            }
        });

        carregarDados();

    }

    private void carregarDados() {

        minhaListas = new ArrayList<MinhaLista>();
        MinhaLista m1 = new MinhaLista( 0, "Imagem 1", getResources().getDrawable(R.drawable.ic_done1) );
        MinhaLista m2 = new MinhaLista( 1, "Imagem 2", getResources().getDrawable(R.drawable.ic_done2) );
        MinhaLista m3 = new MinhaLista( 2, "Imagem 3", getResources().getDrawable(R.drawable.ic_done3) );
        MinhaLista m4 = new MinhaLista( 2, "Carro", getResources().getDrawable(R.drawable.ic_carro) );

        minhaListas.add(m1);
        minhaListas.add(m2);
        minhaListas.add(m3);
        minhaListas.add(m4);

        meuAdapter = new AdapterMinhaLista(this, minhaListas);
        myList1.setAdapter(meuAdapter);

    }

}
