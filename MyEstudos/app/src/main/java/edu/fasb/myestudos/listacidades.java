package edu.fasb.myestudos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listacidades extends AppCompatActivity {

    private List<Cidades> cidades;
    private ImageButton btnNovo;
    private ListView view;
    private CidadesAdapter cidadesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacidades);

        btnNovo = (ImageButton) findViewById(R.id.novaCidades);
        view = (ListView) findViewById(R.id.listaCidades);

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TelaCidade.class);
                i.putExtra("Id", -1);  //para novos registro
                startActivity(i);
            }
        });

        Carregar();
    }

    private void Carregar(){
        cidades = new ArrayList<>();
        cidades.add( new Cidades(1, "Barrerias", "BA") );
        cidades.add( new Cidades(2, "Rio de Janeiro", "RJ") );
        cidades.add( new Cidades(3, "São Paulo", "SP") );
        cidades.add( new Cidades(4, "Barra", "BA") );

        cidadesAdapter = new CidadesAdapter(this, cidades);

        view.setAdapter(cidadesAdapter);

    }


}
