package edu.fasb.aulagti2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resultado extends AppCompatActivity {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        texto = (TextView) findViewById(R.id.LabelResult);

        String n = "";
        if ( getIntent().getExtras() != null ){
            n = getIntent().getStringExtra("Nome");
        }
        //mostrar na tela o que veio no parâmetro "Nome"
        texto.setText( n );
    }

    /**
     * método para fechar a Activity
     * @param v
     */
    public void fechar(View v){
        finish();
    }
}
