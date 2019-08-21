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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_act1;
    private String TAG_APP = "MyAPP";
    private ListView myList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_act1 = (Button) findViewById(R.id.btnTela1);
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

        myList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = myList1.getItemAtPosition( position ).toString();
                Toast.makeText( parent.getContext(), nome , Toast.LENGTH_LONG ).show();
                Log.e(TAG_APP, nome);
            }
        });

    }

}
