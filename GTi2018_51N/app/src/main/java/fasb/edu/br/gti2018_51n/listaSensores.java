package fasb.edu.br.gti2018_51n;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class listaSensores extends AppCompatActivity {

    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sensores);

        label = (TextView) findViewById(R.id.labelSensor);

        Bundle paramento = getIntent().getExtras();

        if (paramento != null) {
            String resultao = paramento.getString("time", "Não deu certo!");
            resultao += "\n" + paramento.getInt("gols") + " x 0\nSão Paulo";
            label.setText(  resultao );
        }


    }
}
