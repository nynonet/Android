package br.edu.fasb.blueunifasb;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    TODO 04 - Variaveis do Layout
    private ImageButton imageButton;
    private TextView textView;
    private Button button;
    private ListView listView;

    //TODO 07 - Variaveis de Programação
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private int BLUETOOTH_HABILITADO = 1;

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
}
