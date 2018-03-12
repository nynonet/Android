package fasb.edu.br.gti2018_51n;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<Sensor> listaSensor;
    private ListView lista;
    private Button BotaoTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fazendo a manipulação via código da minha listview
        lista = (ListView) findViewById(R.id.lwSensor);

        //Ligando o botão do layout a variável declarada no código.
        BotaoTeste = (Button) findViewById(R.id.btnTeste);

        //carrega a minha lista de sensores.
        ListaSensores();


        //procedimento para capturar o click do botão especifico.
        BotaoTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplication(), "Olá!", Toast.LENGTH_SHORT).show();

                //criando a nova janela em memória
                Intent novo = new Intent(getApplication(), listaSensores.class);

                novo.putExtra("time", "Palmeiras");
                novo.putExtra("gols", 2);

                //iniciando a nova janela
                startActivity(novo);

            }
        });



    }

    private void ListaSensores() {
        //capturando a gerenciador de sensores
        sensorManager = ( SensorManager ) getSystemService( SENSOR_SERVICE );

        //carrego todos os sensores
        listaSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);


        //Cria uma lista vazia com os nomes dos sensores.
        List<String> sensorNomes = new ArrayList<>();

        //adiciono o nome do sensor na lista
        for (int i=0; i<listaSensor.size(); i++){
            sensorNomes.add( listaSensor.get(i).getName() );
        }

        //cria um adapter "dados" para ser apresentado na listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sensorNomes);

        //Envia os dados para mostrar no listview
        lista.setAdapter(adapter);
    }
}
