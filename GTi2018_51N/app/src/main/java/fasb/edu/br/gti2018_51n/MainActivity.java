package fasb.edu.br.gti2018_51n;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<Sensor> listaSensor;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fazendo a manipulação via código da minha listview
        lista = (ListView) findViewById(R.id.lwSensor);

        //capturando a gerenciador de sensores
        sensorManager = ( SensorManager ) getSystemService( SENSOR_SERVICE );

        //carrego todos os sensores
        listaSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> sensorNomes = new ArrayList<>();

        //adiciono o nome do sensor na lista
        for (int i=0; i<listaSensor.size(); i++){
            sensorNomes.add( listaSensor.get(i).getName() );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sensorNomes);

        lista.setAdapter(adapter);



    }
}
