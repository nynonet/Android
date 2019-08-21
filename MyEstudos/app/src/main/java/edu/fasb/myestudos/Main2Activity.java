package edu.fasb.myestudos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private Button btn_act2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String nome = "";

        if ( getIntent().getExtras() != null ) {

            nome = getIntent().getStringExtra("Nome");

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
