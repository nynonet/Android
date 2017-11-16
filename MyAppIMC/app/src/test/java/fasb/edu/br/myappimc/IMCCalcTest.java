package fasb.edu.br.myappimc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andeson on 15/11/2017.
 * https://developer.android.com/studio/test/index.html
 */
public class IMCCalcTest {

    @Test
    public void calculando(){
        double peso = 80;
        double altura = 1.80;
        double valor = 24.69;

        IMCCalc imcCalc = new IMCCalc(peso, altura);

        assertEquals(valor, imcCalc.getValorIMC(), 0.0);
    }

}