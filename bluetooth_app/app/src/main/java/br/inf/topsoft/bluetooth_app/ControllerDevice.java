package br.inf.topsoft.bluetooth_app;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ControllerDevice extends AppCompatActivity {

    //    TODO 16 - Declarando as variáveis
    TextView textViewDevice;
    ImageButton imageButtonSend;
    EditText editTextComando;
    static TextView listViewResultado;
//    static ListView listViewResultado;
    BluetoothDevice device;
    static List<String> retornoList = new ArrayList<>();

    // TODO 22 - Usando o BluetoothConectdThread
    BluetoothConectedThread monitorBlue;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO 27 - Fechando a conexão quando sair da activity
        monitorBlue.Cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_device);

        //TODO 17 - Associando os objetos
        textViewDevice = (TextView) findViewById(R.id.lb_controle_device);
        editTextComando = (EditText) findViewById(R.id.edit_controle_comando);
        imageButtonSend = (ImageButton) findViewById(R.id.btn_controle_send);
//        listViewResultado = (ListView) findViewById(R.id.list_controle_resultado);
        listViewResultado = (TextView) findViewById(R.id.list_controle_resultado);

        //TODO 18 - Lendo qual o device foi selecioando.
        Bundle parameto = getIntent().getExtras();
        if (parameto != null) {
            device = (BluetoothDevice) parameto.get("device");
            String informacao = device.getName() + " - " + device.getAddress();
            textViewDevice.setText(informacao);
            //TODO 23 - Iniciando e Startando o monitor bluetooth
            monitorBlue = new BluetoothConectedThread(device);
            monitorBlue.start();
        } else {
            imageButtonSend.setVisibility(View.INVISIBLE);
            textViewDevice.setText(getString(R.string.lbComand_noDevice));
        }

//        listViewResultado.setAdapter(null);
        listViewResultado.setText("");

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
        Toast.makeText(this, "Comando Enviado!", Toast.LENGTH_LONG).show();
        //TODO 26 - Enviando a mensagem
        monitorBlue.SendDados( comando );
    }

    public static Handler handler = new Handler(){
        //TODO 24 - Criando o handler de mensagem
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            byte[] dados = bundle.getByteArray("dados");
            retornoList.add( new String(dados) );
            listViewResultado.setText( retornoList.toString()  );
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(listViewResultado.getContext(), R.layout.support_simple_spinner_dropdown_item, retornoList);
//            listViewResultado.setAdapter(adapter);
        }
    };
}
