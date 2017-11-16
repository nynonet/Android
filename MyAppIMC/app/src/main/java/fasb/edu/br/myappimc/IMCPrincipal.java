package fasb.edu.br.myappimc;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IMCPrincipal extends AppCompatActivity {

    private Button btnclick;
    private EditText ed_peso;
    private EditText ed_altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcprincipal);

        btnclick = (Button) findViewById( R.id.btnCalc );
        ed_peso = (EditText) findViewById( R.id.edit_peso );
        ed_altura = (EditText) findViewById( R.id.edit_altura );


        btnclick.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String msgtela = ed_peso.getText() +
                                         "\n"+ ed_altura.getText();
                        Toast.makeText(getApplication(), msgtela, Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
}
