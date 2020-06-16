package br.edu.fasb.blueunifasb;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

public class BlueThreadConect extends Thread {

    private String LOGAPP = "BLUE-APP";
    private BluetoothSocket bluetoothSocket;
    private InputStream inputDados;
    private OutputStream outputDados;

    private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothDevice dispositivo;
    private boolean lendo = false;

    public BlueThreadConect( BluetoothDevice device ) {
        this.dispositivo = device;
    }

    @Override
    public void run() {

        lendo = false;

        try {
            //para conectar
            bluetoothSocket = dispositivo.createRfcommSocketToServiceRecord(uuid);

            if (bluetoothSocket != null) {
                bluetoothSocket.connect();
            }

            lendo = true;
            AtualizaTela( "Conectado ao dispositivo: "+ dispositivo.getName() );
        } catch (IOException e) {
            Log.i(LOGAPP, "Erro no conectar: ");
            e.printStackTrace();
            AtualizaTela( "Erro ao Conectar no dispositivo: "+ dispositivo.getName() );
        }

        if (bluetoothSocket != null) {
            try {
                inputDados = bluetoothSocket.getInputStream();
                outputDados = bluetoothSocket.getOutputStream();

                byte[] buffer = new byte[1024];
                int bytes;

                while ( lendo ) {
                    bytes = inputDados.read(buffer);
                    String pacote = new String(Arrays.copyOfRange(buffer, 0, bytes));
                    AtualizaTela( pacote );
                }

            } catch (IOException e) {
                Log.i(LOGAPP, "Erro no ler dados: ");
                e.printStackTrace();
            }
        }
    }

    public void EnviaDados(String envia) {
        if (outputDados != null) {
            try {
                outputDados.write(envia.getBytes());
            } catch (IOException e) {
                Log.i(LOGAPP, "Erro no escrever dados: ");
                e.printStackTrace();
            }
        }
    }

    private void AtualizaTela( String dados ){
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("pacoteDados", dados.getBytes());
        message.setData(bundle);

        ActivityComando.correios.sendMessage(message);
    }

    public void Disconectar(){

        try {
            lendo = false;
            bluetoothSocket.close();
        }catch (IOException e) {
            Log.i(LOGAPP, "Erro no desconectar: ");
            e.printStackTrace();
        }

    }
}
