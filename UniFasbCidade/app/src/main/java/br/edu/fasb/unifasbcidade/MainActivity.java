package br.edu.fasb.unifasbcidade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    TODO 01 - Declarando as Variaveis da Activity no XML "id"
    private TextView lbShow;
    private Button btnShow;
    private EditText edit_texto;
    private ImageButton btnNovaTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 02 - Fazendo o vinculo dos ids as variaveis
        lbShow = (TextView) findViewById(R.id.lbShow);
        btnShow = (Button) findViewById(R.id.btnShow);
        edit_texto = (EditText) findViewById(R.id.edit_texto);
        btnNovaTela = (ImageButton) findViewById(R.id.btnNovaTela) ;

        //TODO 03 - Codificando o botão SHOW
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lbShow.setText(edit_texto.getText());
            }
        });

        //TODO 04 - Codificando o botão para chamar a segunda tela
        btnNovaTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui
                Intent intent = new Intent(v.getContext(), SegundaTela.class);
                startActivity(intent);
            }
        });

    }

}
