package br.inf.topsoft.bluetooth_app;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ControllerDevice extends AppCompatActivity {

    //    TODO 16 - Declarando as variáveis
    TextView textViewDevice;
    ImageButton imageButtonSend;
    EditText editTextComando;
    ListView listViewResultado;
    BluetoothDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_device);

        //TODO 17 - Associando os objetos
        textViewDevice = (TextView) findViewById(R.id.lb_controle_device);
        editTextComando = (EditText) findViewById(R.id.edit_controle_comando);
        imageButtonSend = (ImageButton) findViewById(R.id.btn_controle_send);
        listViewResultado = (ListView) findViewById(R.id.list_controle_resultado);

        //TODO 18 - Lendo qual o device foi selecioando.
        Bundle parameto = getIntent().getExtras();
        if (parameto != null) {
            device = (BluetoothDevice) parameto.get("device");
            String informacao = device.getName() + " - " + device.getAddress();
            textViewDevice.setText(informacao);
        } else {
            imageButtonSend.setVisibility(View.INVISIBLE);
            textViewDevice.setText(getString(R.string.lbComand_noDevice));
        }

        listViewResultado.setAdapter(null);

        //TODO 19 - Programando o botão send
        imageButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //caso hajá algo digitado envia...
                if (editTextComando.getText().toString().trim().length() > 0) {
                    EnviarComando(editTextComando.getText().toString()); //chama método de envio.
                }
                editTextComando.setText(""); //limpa campo
            }
        });
    }

    private void EnviarComando(String comando) {
        //TODO 20 - Lendo qual o device foi selecioando.
        Log.i("APP", "Comando:");
        Log.e("APP", comando);

        Toast.makeText(this, "Comando Enviado!", Toast.LENGTH_LONG).show();
    }
}
