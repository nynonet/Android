package fasb.edu.br.crudcliente;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import fasb.edu.br.crudcliente.modal.Cliente;
import fasb.edu.br.crudcliente.modal.dao.ClienteDAO;

public class MainActivity extends AppCompatActivity {



    private Button bCanc, bSalv;
    private RecyclerView recyclerView;
    private ClienteAdapter adapter;
    private Operacao operacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bCanc = (Button) findViewById(R.id.btn_cancel);
        bSalv = (Button) findViewById(R.id.btn_salvar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includeMain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includeCadastro).setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);
                operacao = Operacao.INSERIR;
            }
        });

        bCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.includeMain).setVisibility(View.VISIBLE);
                findViewById(R.id.includeCadastro).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
            }
        });

        bSalv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nome = (EditText)findViewById(R.id.edt_cliente);
                Spinner ufs = (Spinner) findViewById(R.id.edt_estados);
                CheckBox ativo = (CheckBox) findViewById(R.id.edt_status);

                Cliente c;
                if (operacao == Operacao.INSERIR) {
                     c = new Cliente();
                } else {

                     c = new Cliente();
                }

                c.setNome( nome.getText().toString() );

                c.setUf( ufs.getSelectedItem().toString() );

                c.setStatus( ativo.isChecked() );

                ClienteDAO cDao = new ClienteDAO(getBaseContext());
                String msg;
                boolean resp ;
                if (operacao == Operacao.INSERIR) {
                    resp = cDao.Insert(c);
                    msg = "Registro Salvo com sucesso!";
                } else {
                    resp = cDao.Update(c);
                    msg = "Registro Atualizado com sucesso!";
                }

                if ( resp ) {
                    adapter.AdicionarCliente(c);
                    nome.setText("");
                    ufs.setSelection(0);
                    ativo.setChecked(false);

                    Toast.makeText( getApplication() , msg, Toast.LENGTH_SHORT).show();
                    findViewById(R.id.includeMain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includeCadastro).setVisibility(View.INVISIBLE);
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText( getApplication() , "Falha no salvar registro", Toast.LENGTH_SHORT).show();
                }

            }
        });

        CarregaRecycleView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void CarregaRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclelista);

//        recyclerView.setLayoutManager();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ClienteDAO dao = new ClienteDAO(this);
        adapter = new ClienteAdapter(dao.getClientes());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}
