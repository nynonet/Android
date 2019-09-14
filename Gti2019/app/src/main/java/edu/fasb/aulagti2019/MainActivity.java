package edu.fasb.aulagti2019;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn1;  //botão para chamar a tela 2
    private Button btn2;  //botão para chamar a Config
    private TextView textoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean mostra = Configuracao.getConfig(getApplication())
                    .getBoolean("email_visivel", false);

        String email = Configuracao.getConfig(getApplication())
                .getString("edit_email", "tttt@ttt.com");

        btn1 = (Button) findViewById(R.id.btnTela1);
        btn2 = (Button) findViewById(R.id.btnConfig);
        textoEmail = (TextView) findViewById(R.id.txtEmail);

        textoEmail.setText(email);
//        if ( mostra) {
//
//        } else {
//
//        }
        textoEmail.setVisibility( (mostra ? View.VISIBLE : View.INVISIBLE) );

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chamando a tela de configuração
                Intent config = new Intent(getApplication(), Configuracao.class);
                startActivity(config);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("APPMEU", "Deu Certo!");
                Intent novaTela =
                        new Intent( MainActivity.this, Main2Activity.class);

                startActivity( novaTela );
            }
        });
    }
}
