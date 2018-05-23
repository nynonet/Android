package fasb.edu.br.filmes;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //TODO(9) FAZER A DECLARAÇÃO DOS CAMPOS EXISTEN NO LAYOUT
    private TextView titulo;
    private TextView genero;
    private TextView ano;
    private TextView escritor;
    private TextView atores;

    private ListView lista;
    private ImageView imagem;
    private EditText editbusca;

    private Button busca;
    private Button limpa;

    //TODO(10) CRIAR UM LISTA QUE VAI RECEBER OS FILMES USANDO A CLASSE QUE CROU
    private List<Filme> resultadoBusca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO(11) FAZER A REFERENCIA ENTRE OS CAMPOS E OS OBJETOS USANDO O FINDVIEWBYID()
        titulo = (TextView) findViewById(R.id.rp_titulo);
        genero = (TextView) findViewById(R.id.rp_genero);
        ano = (TextView) findViewById(R.id.rp_ano);
        escritor = (TextView) findViewById(R.id.rp_escritor);
        atores = (TextView) findViewById(R.id.rp_atores);
        imagem = (ImageView) findViewById(R.id.imagem);
        lista = (ListView) findViewById(R.id.lista);
        editbusca = (EditText) findViewById(R.id.edit_busca);

        busca = (Button) findViewById(R.id.btnPesquisa);
        limpa = (Button) findViewById(R.id.btnLimpa);

        //TODO(12) PROCEDIMENTO PARA LIMPAR TODA A TELA.
        limpar(); //inicia tudo em branco...

        //TODO(15) PROGRAMANDO O CLICK DO BOTÃO LIMPAR.
        limpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });
        //TODO(16) AÇÃO DO BOTÃO LISTAR.
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO(17) VALIDA SE INFORMOU ALGUM TITULO A BUSCAR
                if (editbusca.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "informe o nome que deseja pesquisar", Toast.LENGTH_LONG).show();
                    return;
                }
                //TODO(18) EXECUTA A CLASSE RESPOSTAVEL POR FAZER A BUSCA
                new Pesquisa().execute();
            }
        });

        //TODO(19) CARREGAR OS DADOS DO FILME SELECIONADO NA LISTA
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO(20) SETANDO OS VALORES DO FILME SELECIONADO
                titulo.setText( resultadoBusca.get(position).getTitulo() );
                ano.setText( resultadoBusca.get(position).getAno() );
                genero.setText( resultadoBusca.get(position).getGenero() );
                escritor.setText( resultadoBusca.get(position).getEscritor() );
                atores.setText( resultadoBusca.get(position).getAtores() );
                imagem.setImageBitmap( resultadoBusca.get(position).getImagemCapa() );
            }
        });
    }
    //TODO(13) CRIANDO O PROCEDIMENTO LIMPAR
    private void limpar(){
        //LIMPA TODAS AS INFORMAÇÕES DA TELA E DA LISTA
        resultadoBusca = new ArrayList<>();
        titulo.setText("");
        ano.setText("");
        genero.setText("");
        escritor.setText("");
        atores.setText("");
        editbusca.setText("");
        imagem.setImageBitmap(null);

        //COLOCA O FOCO DO CURSO NO CAMPO DE BUSCA
        editbusca.requestFocus();

        //PROCEDIMENTO PARA PREENCHER OS DADOS DA CONSULTA
        PreencheLista();
    }
    //TODO(14) CRIANDO O PROCEDIMENTO PARA PREENCHER OS DADOS NA TELA
    private void PreencheLista() {
        ArrayAdapter<Filme> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, resultadoBusca);
        lista.setAdapter(adapter);
    }
    //TODO(21) EXECUTA A PESQUISA COMO UMA TAREFA A PARTE..
    private class Pesquisa extends AsyncTask<Void, Void, List<Filme>>{

        ProgressDialog load;//IRÁ APRESENTAR UMA TELA DE AGUARDANDO PARA O USUÁRIO

        @Override
        protected void onPreExecute() {
            //TODO(22) DEVE-SE INICIALIZAR "CRIAR" O PROGRESS DIALOG.
            //PASSANDO A INFORMAÇÃO PARA O USUÁRIO PARA AGUARDAR...
            load = ProgressDialog.show(MainActivity.this, "Busca de Filmes/Série", "Por Favor Aguarde...");
        }

        @Override
        protected List<Filme> doInBackground(Void... voids) {
            //TODO (23) EXECUTA A BUSCA E DEVOLVE O RESULTADO.

            BuscaFilmes consulta = new BuscaFilmes(MainActivity.this);

            return consulta.getFilmes( editbusca.getText().toString() );
        }

        @Override
        protected void onPostExecute(List<Filme> filmes) {
            //TODO (24) ADICIONA O RESULTADO E FECHA A TELA DE BUSCANDO.
            resultadoBusca = filmes;
            load.dismiss();

            PreencheLista();
        }
    }

}
