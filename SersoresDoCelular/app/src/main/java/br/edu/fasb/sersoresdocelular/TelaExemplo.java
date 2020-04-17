package br.edu.fasb.sersoresdocelular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TelaExemplo extends AppCompatActivity {

    SensorManager sm;
    Sensor sensor;

    ImageView imagem;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_exemplo);

        imagem = (ImageView) findViewById(R.id.imgTemp);
        texto = (TextView) findViewById(R.id.txtNome);

        sm = (SensorManager) getSystemService( SENSOR_SERVICE );
        sensor = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        sm.registerListener( new SensorAmbienteTempertura(), sensor, SensorManager.SENSOR_DELAY_UI );

    }

    private class SensorAmbienteTempertura implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            // >= 35 Quente
            // <= 22 Frio
            // boa
            String nome = "Temperatura ";
            int icone;
            if (event.values[0] >= 35) {
                nome += "quente";
                icone = R.color.colorQuente;
            } else {
                if (event.values[0] <= 22) {
                    nome += "fria";
                    icone = R.color.colorFrio;
                } else {
                    nome += "agradável";
                    icone = R.color.colorNormal;
                }
            }

            nome += "\n >> "+String.valueOf(event.values[0]) + "º";

            texto.setText(nome);

            imagem.setColorFilter( getResources().getColor(icone) );
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
