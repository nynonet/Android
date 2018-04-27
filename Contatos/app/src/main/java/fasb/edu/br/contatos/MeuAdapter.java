package fasb.edu.br.contatos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MeuAdapter extends BaseAdapter {

    //Obter a lista de contatos
    private List<Contato> lista;
    //Obter a Janela que está invocando o MeuAdapter
    private Activity activity;

    /**
     * Método consytrutor da minha classe MeuAdapter
     * @param lista  minha lista
     * @param activity minha Janela
     */
    public MeuAdapter(List<Contato> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        //pergunta quantas linhas possui a minha lista?
        return lista.size(); //devolvo da minha lista o seu tamanho.
        //sendo assim um resultado dinamico. ou seja, auto regulavel
    }

    @Override
    public Object getItem(int position) {
        //pergunta qual o item deseja ou objeto retornar?
        return lista.get(position); //devolvo o objeto selecionado.
    }

    @Override
    public long getItemId(int position) {
        //pergunta qual o código do Item?
        return lista.get(position).getId(); //devolvo o código do item selecionado
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //crio um objeto de meus itens do layout
        final ViewMy viewMy;
        //chama o gerenciador de Layouts do android
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //Verifica se o objeto lista já tem o novo layout
        if (convertView == null){
            //se não tiver cria-se uma vazia
            convertView = inflater.inflate(R.layout.contatolayout, parent, false);
            viewMy = new ViewMy(convertView);
            convertView.setTag(viewMy);
        } else {
            //se tiver busca o layout já criado em mémoria.
            viewMy = (ViewMy) convertView.getTag();
        }
        //preenche os dados do meu Layout
        viewMy.id.setText( String.valueOf(lista.get(position).getId()) );
        viewMy.nome.setText( lista.get(position).getNome() );
        viewMy.fone.setText( lista.get(position).getTelefone() );
        viewMy.selecionado.setChecked( lista.get(position).isMarcado() );
        viewMy.avalia.setTag(position);

        viewMy.selecionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.get(position).setMarcado( viewMy.selecionado.isChecked() );
            }
        });

//        switch (lista.get(position).getFoto()) {
//            case "A": viewMy.foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.cmais) );
//                break;
//            case "B": viewMy.foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.delphi) );
//                break;
//            case "C": viewMy.foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.java) );
//                break;
//            case "D": viewMy.foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.python) );
//                break;
//            case "E": viewMy.foto.setImageDrawable( activity.getResources().getDrawable(R.drawable.swift) );
//                break;
//            default: ;
//        }
        //fica observando e modificando os valores quando avaliar o ratingBar
        viewMy.avalia.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //mostra na tela o valor setado
                viewMy.nota.setText(String.valueOf(rating));
                //grava no item da lista o valor que por ultimo fora setado.
                lista.get(position).setValor( rating );
            }
        });

        //devolve a lista p/o listview
        return convertView;
    }

    private static class ViewMy {
        //declara-se todos os componente do Layout
//        ImageView foto;
        TextView id;
        TextView nome;
        TextView fone;
        TextView nota;
        RatingBar avalia;
        CheckBox selecionado;

        /**
         * Vincula no metodo construtor todos os objeto do layout
         * a classe MeuAdapter
         * "Referência os objetos"
         * @param view
         */
        public ViewMy(View view){
//            foto = (ImageView) view.findViewById(R.id.foto);
            id = (TextView) view.findViewById(R.id.lb_id);
            nome = (TextView) view.findViewById(R.id.lb_nome);
            fone = (TextView) view.findViewById(R.id.lb_fone);
            nota = (TextView) view.findViewById(R.id.lb_nota);
            avalia = (RatingBar) view.findViewById(R.id.nota);
            selecionado = (CheckBox) view.findViewById(R.id.ckexemplo);
        }

    }
}
