package fasb.edu.br.myappimc;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IMCPrincipal extends AppCompatActivity {
//    http://www.mundoboaforma.com.br/imc-novo-calculo-o-que-muda-entenda-mais/
//    http://www.mundoboaforma.com.br/como-identificar-rapidamente-seu-percentual-de-gordura-corporal/
    private Button btnclick;
    private EditText ed_peso;
    private EditText ed_altura;
    private double imcValor = 0;
    private ImageView imagem;
    private TextView label_result;
    private TextView label_mensag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcprincipal);

        btnclick = (Button) findViewById( R.id.btnCalc );
        ed_peso = (EditText) findViewById( R.id.edit_peso );
        ed_altura = (EditText) findViewById( R.id.edit_altura );
        imagem = (ImageView) findViewById(R.id.Imagem);
        label_result = (TextView) findViewById(R.id.label_result);
        label_mensag = (TextView) findViewById(R.id.label_mensag);


        btnclick.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (ed_peso.getText().toString().isEmpty()) {
                            Toast.makeText(getApplication(), "Informe o Peso!",
                                    Toast.LENGTH_LONG).show();
                            ed_peso.requestFocus();
                            return;
                        }

                        if (ed_altura.getText().toString().isEmpty()) {
                            Toast.makeText(getApplication(), "Informe a Altura!",
                                    Toast.LENGTH_LONG).show();
                            ed_altura.requestFocus();
                            return;
                        }

                        double peso = Double.parseDouble( ed_peso.getText().toString() );
                        double altura = Double.parseDouble( ed_altura.getText().toString() );

                        IMCCalc calc = new IMCCalc(peso, altura);
                        imcValor = calc.getValorIMC();

                        String msgtela = "Calculando...";
                        Toast.makeText(getApplication(), msgtela, Toast.LENGTH_SHORT).show();
                        MostraResultado(view);
                    }
                }
        );

    }

    private void MostraResultado(View view) {
        if ( imcValor == 0 ) {
            //não faz nada!
        } else {
            label_result.setText( getResources().getString(R.string.result).replace( "%s", String.valueOf(imcValor) ) );
            if (imcValor < 18.5) {
                //muito magro
                imagem.setImageResource( R.drawable.imc_1 );
                label_mensag.setText( getResources().getString(R.string.msg1) );
            }

            if ( (imcValor >= 18.5) && (imcValor < 25.0) ) {
               //Normal
                imagem.setImageResource( R.drawable.imc_2 );
                label_mensag.setText( getResources().getString(R.string.msg2) );
            }

            if ( (imcValor >= 25) && (imcValor < 30)) {
                //sobrepeso
                imagem.setImageResource( R.drawable.imc_3 );
                label_mensag.setText( getResources().getString(R.string.msg3) );
            }

            if ( imcValor > 30) {
                //obeso
                imagem.setImageResource( R.drawable.imc_4 );
                label_mensag.setText( getResources().getString(R.string.msg4) );
            }

        }
    }
}
