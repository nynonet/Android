package edu.fasb.myestudos;

import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    private Button btn_act2;
    private ImageView ico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String nome = "";
        ico = (ImageView) findViewById(R.id.imageView);

        if ( getIntent().getExtras() != null ) {

            if (getIntent().getStringExtra("Nome") != null) {
                nome = getIntent().getStringExtra("Nome");
            }

            if (getIntent().getParcelableExtra("Obj")!=null) {
                MinhaLista m = (MinhaLista) getIntent().getParcelableExtra("Obj");

                nome = "Valor =: " + m.getNome();

//                Log.i("AJM", m.getIcone().toString());
//
//                ico.setImageDrawable( m.getIcone() );
            }

        }
        btn_act2 = (Button) findViewById(R.id.btnTela2);

        btn_act2.setText( nome );


        btn_act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
