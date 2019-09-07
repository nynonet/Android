package edu.fasb.aulagti2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.fasb.aulagti2019.lista_person.AdapterCurso;
import edu.fasb.aulagti2019.lista_person.Curso;

public class Main2Activity extends AppCompatActivity {

    private Button btn1;        //botão voltar do layout
    private ListView list1;     //listview de cursos
    private List<Curso> cursos; //Lista de cursos
    private AdapterCurso adapterCurso;  //Adapater do Curso p/nossa lista view personalizada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  //cria a instância
        setContentView(R.layout.activity_main2);  //carrega o layout da Activity

        btn1 = (Button) findViewById(R.id.btnTela2);    //vincula o botão
        list1 = (ListView) findViewById(R.id.listview_gti); //vincula o listview

        //programando o botão
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //programando o listview
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String nome = list1.getItemAtPosition(position).toString();
//                Log.i("APPMEU", nome);
//                Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_SHORT).show();


                Intent telaResult = new Intent(getApplicationContext(), resultado.class);
                telaResult.putExtra("ObjCurso", cursos.get( position ));
//                telaResult.putExtra("Nome", nome);
                startActivity( telaResult );
            }
        });

        //chamar a função p/carregar os dados na tela
        CarregaDados();

    }

    private void CarregaDados() {
        this.cursos = new ArrayList<>(); //cria a lista de cursos

        //Cria um curso dentro da lista
        this.cursos.add( new Curso(1, "Programação",
                R.drawable.ic_programa) );

        this.cursos.add( new Curso(2, "Acorda p/Vida",
                R.drawable.ic_acorda) );

        this.cursos.add( new Curso(3, "Sexta Feira - Recarregar",
                R.drawable.ic_battery) );

        //Cria o adapter personalizado com a lista de cursos criada
        adapterCurso = new AdapterCurso(this, cursos);

        //informa a listview quais são os dados e como deve apresenta-lós.
        list1.setAdapter( adapterCurso );
    }

}
