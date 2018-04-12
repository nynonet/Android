package fasb.edu.br.gti2018_51n;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class aproc extends AppCompatActivity {

    private LinearLayout fundao;
    private TextView valor;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aproc);

        fundao = (LinearLayout) findViewById(R.id.fundoCor);
        valor = (TextView) findViewById(R.id.valorSensor);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY );

        sensorManager.registerListener(new observaSensor(), sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    class observaSensor implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            //String sValor = String.format("%2.2f", event.values[0]);
            String sValor = String.valueOf(event.values[0]);
            valor.setText( sValor );

            if (event.values[0] >=7){
                fundao.setBackgroundResource(R.color.Aprovado);
            } else {
                if (event.values[0] < 3) {
                    fundao.setBackgroundResource(R.color.Reprovado);
                } else {
                    fundao.setBackgroundResource(R.color.Exame);
                }
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
