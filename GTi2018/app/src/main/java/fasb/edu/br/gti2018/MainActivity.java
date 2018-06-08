package fasb.edu.br.gti2018;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Semestre> listaSemestre = new ArrayList<>();
    private List<Disciplina> listaDisciplina = new ArrayList<>();
    private ListView listaXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaXML = (ListView) findViewById(R.id.semestre);

        preencheSemestres();

        ArrayAdapter<Semestre> adapter = new ArrayAdapter<Semestre>(
                this, R.layout.support_simple_spinner_dropdown_item ,
                listaSemestre );

        listaXML.setAdapter(adapter);

        listaXML.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ///
                String a = listaSemestre.get(  position ).getNome();
                Toast.makeText(getApplication(), a, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplication(), detalhes.class);

                List<Disciplina> lista = new ArrayList<Disciplina>();

                for ( Disciplina d : listaDisciplina ) {
                    if (d.getSemestre().getId() == position+1) {
                        lista.add(d);
                    }
                }
                List<teste> tt = new ArrayList<>();
                teste t1 = new teste(1, "Eu teste");
                tt.add( t1 );

                i.putExtra("aa", (Parcelable) t1);
                i.putExtra("lista", (Serializable) lista);
                startActivity(i);

            }
        });

    }

    private void preencheSemestres(){
        Semestre s1 = new Semestre(1, "1 - Semestre");
        listaSemestre.add( s1 );

        listaDisciplina.add( new Disciplina( 1, s1, "ALGORITMO" ) );
        listaDisciplina.add( new Disciplina( 2, s1, "ARQ. COMPUT" ) );
        listaDisciplina.add( new Disciplina( 3, s1, "ÉTICA" ) );
        listaDisciplina.add( new Disciplina( 4, s1, "GESTÃO EMPR." ) );
        listaDisciplina.add( new Disciplina( 5, s1, "REDES" ) );

        Semestre s2 = new Semestre(2, "2 - Semestre");
        listaSemestre.add( s2 );

        listaDisciplina.add( new Disciplina( 1, s2, "GESTÃO E." ) );
        listaDisciplina.add( new Disciplina( 2, s2, "PORTUG" ) );
        listaDisciplina.add( new Disciplina( 3, s2, "INTRO.. BD" ) );
        listaDisciplina.add( new Disciplina( 4, s2, "PROG 1" ) );
        listaDisciplina.add( new Disciplina( 5, s2, "PROJETO DE REDES" ) );
        listaDisciplina.add( new Disciplina( 6, s2, "PROJETO DISP I" ) );

        Semestre s3 = new Semestre(3, "3 - Semestre");
        listaSemestre.add( s3 );
        listaDisciplina.add( new Disciplina( 1, s3, "D1" ) );
        listaDisciplina.add( new Disciplina( 3, s3, "D2" ) );

        Semestre s4 = new Semestre(4, "4 - Semestre");
        listaSemestre.add( s4 );
        listaDisciplina.add( new Disciplina( 1, s4, "M1" ) );
        listaDisciplina.add( new Disciplina( 2, s4, "M2" ) );
        listaDisciplina.add( new Disciplina( 3, s4, "M3" ) );

        Semestre s5 = new Semestre(5, "5 - Semestre");
        listaSemestre.add( s5 );
        listaDisciplina.add( new Disciplina( 1, s5, "T1" ) );
        listaDisciplina.add( new Disciplina( 2, s5, "T2" ) );
        listaDisciplina.add( new Disciplina( 3, s5, "T3" ) );
    };
}
