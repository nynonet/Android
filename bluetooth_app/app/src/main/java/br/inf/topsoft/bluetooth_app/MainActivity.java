package br.inf.topsoft.bluetooth_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

//    TODO 05 - Declarando as variaveis da Activity
    private ListView listDispositivos;
    private ImageButton btnAtivar;
    private TextView lbMensagens;
    private Button btnLista;

//    TODO 08 - Variaveis de Controle da Tela
    private static final int BLUETOOTH_STATUS = 1;
    private BluetoothAdapter bluetoothAdapter;
    private List<BluetoothDevice> dispositivos = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TODO 06 - Realizando o vinculo das variaveis de controle da activity
        listDispositivos = (ListView) findViewById(R.id.listDispositivos);
        btnAtivar = (ImageButton) findViewById(R.id.btnAtivar);
        lbMensagens = (TextView) findViewById(R.id.lb_mensagens);
        btnLista = (Button) findViewById(R.id.btnLista);

//        TODO 07 - Codificação inical
        btnAtivar.setImageResource( R.drawable.bluetooth_off );
        lbMensagens.setText("");
        listDispositivos.setAdapter(null);
        btnLista.setVisibility(View.INVISIBLE);

        btnAtivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtivarDesativar(); //chamando o ativo e desativar
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atualizar a lista de bluetooth
//        TODO 10 -  Pegando os dispositivos pareados
                dispositivos = new ArrayList<>();

                //capturando os dispositivos pareados e adicionado na lista
                for (BluetoothDevice dispositivo: bluetoothAdapter.getBondedDevices()){
                    dispositivos.add( dispositivo );
                }

                AdapterDevices adapterDevices = new AdapterDevices(MainActivity.this, dispositivos);

//                ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<BluetoothDevice>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, dispositivos);

                listDispositivos.setAdapter(adapterDevices);

                String msg = getString(R.string.msgBluetoothInfEncontrado);
                lbMensagens.setText(msg.replace("n%", String.valueOf(dispositivos.size())));

            }
        });

    }

    //        TODO 09 - Codificando a resposta do status do bluetooth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //confere qual a variavel de controle, pois podemos tar mais de uma..
        switch (requestCode){
            case BLUETOOTH_STATUS:
                //checa o resultado
                if( (resultCode == RESULT_OK) || (bluetoothAdapter.getState()==BluetoothAdapter.STATE_ON)) {
                    lbMensagens.setText(getString(R.string.msgBluetoothOk));
                    btnAtivar.setImageResource(R.drawable.bluetooth_on);
                    btnLista.setVisibility(View.VISIBLE);
                } else {
                    btnAtivar.setImageResource(R.drawable.bluetooth_off);
                    lbMensagens.setText(getString(R.string.msgBluetoothFalha));
                    btnLista.setVisibility(View.INVISIBLE);
                }

                break;
        }

    }

    //        TODO 08 - Codificando o status do bluetooth, se presente e ativo
    private void AtivarDesativar(){

        if ( bluetoothAdapter == null ) {
            //Irá Ativar

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            //verificando se há suporte ao bluetooth
            if ( bluetoothAdapter == null ) {
                lbMensagens.setText(getResources().getString(R.string.msgBluetoothAusente));
                return;
            }

            //verifica se encontra-se ativo e não entando pede sua ativação!
            if (!bluetoothAdapter.isEnabled()) {
                Intent habilitabluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(habilitabluetooth, BLUETOOTH_STATUS);
            } else  {

                //caso esteja ativo verifica se está online
                if (bluetoothAdapter.getState()==BluetoothAdapter.STATE_ON) {
                    lbMensagens.setText(getString(R.string.msgBluetoothOk));
                    btnAtivar.setImageResource(R.drawable.bluetooth_on);
                    btnLista.setVisibility(View.VISIBLE);
                }

            }

        } else {
            //Irá Desativar
            bluetoothAdapter.disable();
            btnAtivar.setImageResource(  R.drawable.bluetooth_off );
            bluetoothAdapter = null;
            lbMensagens.setText(getString(R.string.msgBluetoothOff));
            btnLista.setVisibility(View.INVISIBLE);
            listDispositivos.setAdapter(null);
        }

    }


}
