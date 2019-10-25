package edu.fasb.aulagti2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.fasb.aulagti2019.DataBase.CursoController;
import edu.fasb.aulagti2019.lista_person.Curso;

public class resultado extends AppCompatActivity {

    private EditText texto;
    private TextView titulo;
    private Spinner icone;
    private Button btnSalvar;
    private Curso cursoAltera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        texto = (EditText) findViewById(R.id.resultado_edit_curso);

        titulo = (TextView) findViewById(R.id.resultado_status_label);

        icone = (Spinner) findViewById(R.id.resultado_edit_imagem);

        btnSalvar = (Button) findViewById(R.id.resultado_salvar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Curso c = new Curso();

               c.setTexto( texto.getText().toString() );

                int i = (int) icone.getSelectedItemPosition();

                switch ( i ){
                    case 0 : c.setIcone( R.drawable.ic_acorda ); break;
                    case 1 : c.setIcone( R.drawable.ic_battery ); break;
                    case 2 : c.setIcone( R.drawable.ic_programa ); break;
                }

               String msg;
                if (cursoAltera == null) {
                    msg = new CursoController(getApplication()).Insert(c);
                } else {
                    c.setId( cursoAltera.getId() ); //pegar o ID do objeto a alterar
                    msg = new CursoController(getApplication()).Update(c);
                }

               Toast.makeText(v.getContext(), msg, Toast.LENGTH_LONG).show();

               finish();
            }
        });

        if ( getIntent().getExtras() != null ){


            if (getIntent().getParcelableExtra("ObjCurso")!=null){
                //se objeto curso existir
                Curso c = (Curso) getIntent().getParcelableExtra("ObjCurso");
                //preenchedo o nome do curso na tela
                texto.setText( c.getTexto() );

//                Log.i("EU",  );

                //preenchedo o id do curso na tela
                titulo.setText(
                        getResources().getString(R.string.resultado_lab_titulo_alt) );

                Log.i("EU", "ID do curso em edição: "+ c );

                //mostrando o Icone do curso na tela
                for (int i =0; i<icone.getCount()-1; i++) {
                    if ( icone.getItemAtPosition(i).toString().equals( getString(c.getIcone()) ) ) {
                        icone.setSelection(i);
                    }
                }

                cursoAltera = c;
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
