package br.edu.fasb.blueunifasb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    TODO 04 - Variaveis do Layout
    private ImageButton imageButton;
    private TextView textView;
    private Button button;
    private ListView listView;

    //TODO 07 - Variaveis de Programação
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private static final int BLUETOOTH_HABILITADO = 1;
    private List<BluetoothDevice> bluetoothPareados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 05 - Vinculando as variaveis local com do layout
        imageButton = (ImageButton) findViewById(R.id.btnAtiva);
        textView = (TextView)findViewById(R.id.lbMensagems);
        button = (Button) findViewById(R.id.btnBusca);
        listView = (ListView) findViewById(R.id.listaDispositivos);

        //TODO 06 - Programando os recursos visuais
        imageButton.setImageResource( R.drawable.blueInativo );
        button.setVisibility(View.INVISIBLE);
        listView.setAdapter(null);

        //TODO 09 - Programando o botão iniciar
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConectarDesconectar();
            }
        });

        //TODO 12 - Programando o botão listar
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaPareados();
            }
        });
    }

    private void ConectarDesconectar(){
        //TODO 08 - Programando o BlueTooth
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if ( bluetoothAdapter == null) {
            textView.setText(R.string.msg_blue_noblue);
            return;
        }

        if ( ! bluetoothAdapter.isEnabled() ){ //Caso tenha bluetooth e não está ativo
            Intent ativablue = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult( ativablue, BLUETOOTH_HABILITADO );
        } else {
            //caso o bluetooth já se encontre ativo
            imageButton.setImageResource(R.drawable.blueAtivo);
            button.setVisibility(View.VISIBLE);
            textView.setText(R.string.msg_blue_ok);
        }

    }

    private void ListaPareados(){
        //TODO 11 - Listanto e mostrando os dispositivos pareados.
        bluetoothPareados = new ArrayList<>();
        for ( BluetoothDevice d : bluetoothAdapter.getBondedDevices() ){
//            String device = d.getName() + " - " + d.getAddress();
//            bluetoothPareados.add(device);
            bluetoothPareados.add(d);
        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
//                R.layout.support_simple_spinner_dropdown_item, bluetoothPareados);

        AdapterDevices adapterDevices = new AdapterDevices(this, bluetoothPareados);
        //mostra na tela p/o usuário
//        listView.setAdapter(adapter);
        listView.setAdapter(adapterDevices);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //TODO 10 - Reescrevendo o Método onActivityResult para ler o status do Bluetooth
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case BLUETOOTH_HABILITADO :
                    //case o resultado do code for igual ao bluetooth habilitado faça
                if (resultCode == RESULT_OK || bluetoothAdapter.isEnabled() ) {
                    imageButton.setImageResource(R.drawable.blueAtivo);
                    button.setVisibility(View.VISIBLE);
                    textView.setText(R.string.msg_blue_ok);
                } else {
                    imageButton.setImageResource(R.drawable.blueInativo);
                    button.setVisibility(View.INVISIBLE);
                    textView.setText(R.string.msg_blue_falha);
                }
                break;
            default:;
        }
    }
}
