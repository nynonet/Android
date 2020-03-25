package br.inf.topsoft.bluetooth_app;

import android.app.Activity;
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

//TODO 21 - Configurando o controller do bluetooth
public class BluetoothConectedThread extends Thread {
    private String TAGAPP = "BLU-APP";          //ID para debugar erros.
    private BluetoothSocket bluetoothSocket;    //responsável pela conexão
    private InputStream inputDados = null;      //Entrada de Dados
    private OutputStream outputDados = null;    //Saída de Dados
    private UUID keyUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothDevice deviceConexao;
    private boolean lendo = false;

    //criando o método construtor passando o device que iremos conectar
    public BluetoothConectedThread( BluetoothDevice device) {
        this.deviceConexao = device;
    }

    @Override
    public void run() {
        //reescrever o métido run da thread

        lendo = true;

        try {
            //inicia um conexão com o dispositivo
            bluetoothSocket = deviceConexao.createRfcommSocketToServiceRecord(keyUUID);

            if (bluetoothSocket != null) {
                bluetoothSocket.connect();
            }
        } catch (IOException e) {
            Log.e(TAGAPP, "Erro na conexão!");
            e.printStackTrace();
            AtualizaTela( "Veja o log de erro." );
        }

        if ( bluetoothSocket != null ) {
//        if ( (bluetoothSocket != null) && (bluetoothSocket.isConnected()) ) {
            String info = "Conectado à "+deviceConexao.getName();
            AtualizaTela( info );

            try {
                inputDados = bluetoothSocket.getInputStream();
                outputDados = bluetoothSocket.getOutputStream();

                byte[] buffer = new byte[2048];
                int bytes;

                while (lendo) {
                    bytes = inputDados.read(buffer);
                    AtualizaTela( new String(Arrays.copyOfRange(buffer, 0 ,bytes))  );
                }

            }catch (IOException e){
                Log.e(TAGAPP, "Erro na conexão!");
                e.printStackTrace();
                AtualizaTela( "Veja o log de erro." );
            }
        }
    }

    private void AtualizaTela(String msg){
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("dados", msg.getBytes());
        message.setData(bundle);
        Log.e(TAGAPP, "Msg: "+ msg.toString());
        //TODO 25 - Devolvendo a mensagem a tela de controle
        ControllerDevice.handler.sendMessage(message);
    }

    public void SendDados(String msg) {
        if (outputDados != null){
            try {
                outputDados.write(msg.getBytes());
            } catch (IOException e) {
                Log.e(TAGAPP, "Erro na conexão!");
                e.printStackTrace();
            }
        }
    }

    public void Cancel() {
        try {
            lendo = false;
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lendo = false;
    }
}
