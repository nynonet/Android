package br.edu.fasb.blueunifasb;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityComando extends AppCompatActivity {

    //TODO 19 - Declarando as variaveis
    private TextView nome;
    private TextView mac;
    private EditText comando;
    private ImageButton botaoEnvia;
    private ListView resultados;

    private BluetoothDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comando);
        //TODO 20 - Vinculando os objetos do XML a Variável declarada
        nome = (TextView) findViewById(R.id.lb_comando_nome);
        mac = (TextView) findViewById(R.id.lb_comando_mac);
        comando = (EditText) findViewById(R.id.edit_comando);
        botaoEnvia = (ImageButton) findViewById(R.id.btn_comando);
        resultados = (ListView) findViewById(R.id.listaComandos);
        //pegando os paramêtros passado pelo adapter
        Bundle paramento = getIntent().getExtras();
        if (paramento != null){
            this.device = (BluetoothDevice) paramento.get("device");
            nome.setText(this.device.getName());
            mac.setText(this.device.getAddress());
        }
    }
}
