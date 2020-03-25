package br.edu.fasb.sersoresdocelular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sManager;
    ListView listView;
    List<Sensor> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listView = (ListView) findViewById(R.id.listaSensores);

        lista.addAll( sManager.getSensorList(Sensor.TYPE_ALL) );

        ArrayAdapter<Sensor> adapter = new ArrayAdapter<Sensor>(this,
                R.layout.support_simple_spinner_dropdown_item,
                lista
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = "Nome: " + lista.get( position ).getName() + "\n"+
                              "Vendor: " + lista.get( position ).getVendor() + "\n"+
                              "StringType: " + lista.get( position ).getStringType() + "\n"+
                              "Versão: " + lista.get( position ).getVersion() + "\n";


                Toast.makeText(getBaseContext(), "Cliquei na lista \n"+nome,
                        Toast.LENGTH_SHORT).show();
            }
        });

        listView.setAdapter( adapter );





    }
}
