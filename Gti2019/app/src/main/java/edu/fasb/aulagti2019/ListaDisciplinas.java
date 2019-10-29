package edu.fasb.aulagti2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.fasb.aulagti2019.DataBase.DisciplinaController;
import edu.fasb.aulagti2019.lista_person.Disciplina;

public class ListaDisciplinas extends AppCompatActivity {

    private ListView lista;
    private List<Disciplina> disciplinas;

    private String id_curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplinas);

        lista = (ListView) findViewById(R.id.IdListDisciplinas);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //matéria...

//                Intent telaMateria = new Intent( getApplicationContext(), telaMateria.class );
//
//                telaMateria.putExtra("id", disciplinas.get(position).getId() );
//
//                startActivity(telaMateria);

                Log.i("EU", "Id Disciplina: "+ disciplinas.get(position).getId() );
            }
        });

        if (getIntent().getExtras() != null) {
            id_curso = getIntent().getExtras().get("id").toString();

            carregadados();
        }

    }

    public void carregadados() {
        String condicao = " WHERE id_curso = " + id_curso;
        disciplinas = new DisciplinaController( getApplication() ).SelectDisciplina( condicao );

        ArrayAdapter<Disciplina> adapter = new ArrayAdapter<Disciplina>(
                        this,
                        R.layout.support_simple_spinner_dropdown_item,
                        disciplinas
                );

        lista.setAdapter( adapter );

    }

}
