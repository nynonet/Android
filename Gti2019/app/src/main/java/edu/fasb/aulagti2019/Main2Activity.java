package edu.fasb.aulagti2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Button btn1;
    private ListView list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = (Button) findViewById(R.id.btnTela2);
        list1 = (ListView) findViewById(R.id.listview_gti);

        //programando o botão
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //programando o listview
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome = list1.getItemAtPosition(position).toString();
                Log.i("APPMEU", nome);
                Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_SHORT).show();

                Intent telaResult = new Intent(getApplicationContext(), resultado.class);

                telaResult.putExtra("Nome", nome);

                startActivity( telaResult );


            }
        });

    }
}
