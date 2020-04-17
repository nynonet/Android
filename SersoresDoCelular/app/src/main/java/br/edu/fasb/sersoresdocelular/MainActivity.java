package br.edu.fasb.sersoresdocelular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Button btnExemplo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listView = (ListView) findViewById(R.id.listaSensores);

        lista.addAll( sManager.getSensorList(Sensor.TYPE_ALL) );

        AdapterSensorLista adapter = new AdapterSensorLista(lista, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent( getApplication(), TeladoSensor.class );
                intent.putExtra("sensor_nome", lista.get(position).getName() );
                intent.putExtra("sensor_vendor", lista.get(position).getVendor() );
                intent.putExtra("sensor_stringType", lista.get(position).getStringType() );
                intent.putExtra("sensor_versao", lista.get(position).getVersion() );
                intent.putExtra("type_id", lista.get(position).getType());
                startActivity( intent );

                Toast.makeText(getBaseContext(), "Essa foi a opção escolhida!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        listView.setAdapter( adapter );

        btnExemplo = (Button) findViewById(R.id.btnExemploSensor);
        btnExemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TelaExemplo.class);
                startActivity(intent);
            }
        });


    }
}
