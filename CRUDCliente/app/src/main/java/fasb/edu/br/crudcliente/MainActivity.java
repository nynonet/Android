package fasb.edu.br.crudcliente;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import fasb.edu.br.crudcliente.modal.Cliente;
import fasb.edu.br.crudcliente.modal.dao.ClienteDAO;

public class MainActivity extends AppCompatActivity {

    private Button bCanc, bSalv;



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
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ClienteDAO c = new ClienteDAO(getBaseContext());
               String mos = "";
               for (Cliente m : c.getClientes()){
                   mos = mos + m.getNome()+"\n";
               }
               Toast.makeText(getApplication(), mos, Toast.LENGTH_SHORT).show();
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

                Cliente c = new Cliente();

                c.setNome( nome.getText().toString() );

                c.setUf( ufs.getSelectedItem().toString() );

                c.setStatus( ativo.isChecked() );

                ClienteDAO cDao = new ClienteDAO(getBaseContext());
                if (cDao.Insert(c)) {

                    nome.setText("");
                    ufs.setSelection(0);
                    ativo.setChecked(false);

                    Toast.makeText( getApplication() , "Registro Salvo", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.includeMain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includeCadastro).setVisibility(View.INVISIBLE);
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText( getApplication() , "Falha no salvar registro", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
}
