package br.edu.fasb.sersoresdocelular;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TeladoSensor extends AppCompatActivity {

    private TextView s_nome;
    private TextView s_vendor;
    private TextView s_stringType;
    private TextView s_versao;
    private TextView s_resultado;

    private Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telado_sensor);

        s_nome = ( TextView ) findViewById(R.id.sensor_Nome);
        s_vendor = ( TextView ) findViewById(R.id.sensor_Vendor);
        s_stringType = ( TextView ) findViewById(R.id.sensor_StringType);
        s_versao = ( TextView ) findViewById(R.id.sensor_Versao);
        s_resultado = ( TextView ) findViewById(R.id.valores_do_sesor);

        Bundle dados = getIntent().getExtras();
        int idType = -1;
        if ( dados != null ) {
            //se sim
            s_nome.setText( dados.getString("sensor_nome") );
            s_vendor.setText( dados.getString("sensor_vendor") );
            s_stringType.setText( dados.getString("sensor_stringType") );
            s_versao.setText( String.valueOf( dados.getInt("sensor_versao") ) );
            idType = dados.getInt("type_id");
        }

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(idType);

        sm.registerListener( new MeuControle(), sensor, SensorManager.SENSOR_DELAY_UI);
    }

    private class MeuControle implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            String resultado = "";

            for (float e : event.values){
                resultado += String.valueOf(e) + "\n";
            }

            s_resultado.setText( resultado );

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }


}
