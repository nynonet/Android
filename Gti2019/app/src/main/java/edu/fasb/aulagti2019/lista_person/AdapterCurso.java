package edu.fasb.aulagti2019.lista_person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.fasb.aulagti2019.R;

/**
 * classe de apoio a personalização de uma ListView
 */
public class AdapterCurso extends BaseAdapter {

    private List<Curso> lista;      //cria minha lista de cursos
    private LayoutInflater inflater; // criar um layout

    /**
     * Método construtor que recebe um contexto e uma lista de custo.
     * @param context
     * @param lista
     */
    public AdapterCurso(Context context, List<Curso> lista) {
        this.lista = lista; //recebe a lista de curso
        this.inflater = LayoutInflater.from(context); //cria um layout para o contexto
    }

    @Override
    public int getCount() {
        return lista.size(); //retona o tamanho da lista
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position); //retona o objeto selecionado da lista
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId(); //retorna o ID do objeto selecionado
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Auxiliar obj; //declaramos um objeto auxiliar

        if ( view == null) {    //verificamos se já existe um layout carregado
            //cria o layoute na variavel View do Item.
            view = inflater.inflate(R.layout.minhalistview, null);
            obj = new Auxiliar(); //criou o objeto auxiliar

            //vincula a imagemview auxiliar ao do layout personalizado
            obj.icone = (ImageView) view.findViewById(R.id.iconeLista);

            //vincula a textview auxiliar ao do layout personalizado
            obj.texto = (TextView) view.findViewById(R.id.textoLista);

        } else {    //se já existe criado só vincula os dados ao objeto auxiliar
            obj = (Auxiliar) view.getTag(); //atribui o objeto vindo do ListView
        }

        //passando o valor da lista p/o objeto visual
        obj.texto.setText( lista.get(position).getTexto() );
        obj.icone.setImageResource( lista.get(position).getIcone() );

        //retorna a view que fora personalizada.
        return view;
    }

    /**
     * cria uma classe só com os objetos que estão em nosso layout
     * personalizado para auxiliar na atribuição de valores.
     */
    private class Auxiliar {
        ImageView icone;
        TextView texto;
    }
}
