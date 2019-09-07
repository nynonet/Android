package edu.fasb.aulagti2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.fasb.aulagti2019.lista_person.Curso;

public class resultado extends AppCompatActivity {

    private TextView texto;
    private TextView idTexto;
    private ImageView icone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        texto = (TextView) findViewById(R.id.LabelResult);
        idTexto = (TextView) findViewById(R.id.textoIdResultado);
        icone = (ImageView) findViewById(R.id.iconeResultado);

        if ( getIntent().getExtras() != null ){


            if (getIntent().getParcelableExtra("ObjCurso")!=null){
                //se objeto curso existir
                Curso c = (Curso) getIntent().getParcelableExtra("ObjCurso");
                //preenchedo o nome do curso na tela
                texto.setText( c.getTexto() );
                //preenchedo o id do curso na tela
                idTexto.setText( "Id: "+ c.getId() );
                //mostrando o Icone do curso na tela
                icone.setImageDrawable( getResources().getDrawable(c.getIcone()) );
            }

        }

    }

    /**
     * método para fechar a Activity
     * @param v
     */
    public void fechar(View v){
        finish();
    }
}
