package br.edu.fasb.blueunifasb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityComando extends AppCompatActivity {

    //TODO 19 - Declarando as variaveis
    private TextView nome;
    private TextView mac;
    private EditText comando;
    private ImageButton botaoEnvia;
    private static ListView resultados;

    private BluetoothDevice device;

    private BlueThreadConect conexao;
    private static List<String> dadosRecebidos = new ArrayList<>();

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

        conexao = new BlueThreadConect( this.device );
        conexao.start();

        botaoEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conexao.EnviaDados( comando.getText().toString() );
            }
        });
    }

    public static Handler correios = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            byte[] dados = bundle.getByteArray("pacoteDados");
            String mensagem = new String(dados);
            Log.i("retorno", mensagem );
            dadosRecebidos.add( mensagem );

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(resultados.getContext(),
                    R.layout.support_simple_spinner_dropdown_item, dadosRecebidos);

            resultados.setAdapter( adapter );
        }
    };
}
