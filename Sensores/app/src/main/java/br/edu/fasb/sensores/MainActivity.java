package br.edu.fasb.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.lb_sensores);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        String str = "Segue-se os sensores disponíveis em seu aparelho: \n\n";
        for (Sensor s : sensorManager.getSensorList(Sensor.TYPE_ALL)){
            str += s.getName() + "\n";
        }

        textView.setText(str);

    }



}
