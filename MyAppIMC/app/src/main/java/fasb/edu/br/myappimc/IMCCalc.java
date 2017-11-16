package fasb.edu.br.myappimc;

/**
 * Created by Andeson on 13/11/2017.
 */

public class IMCCalc {
    private double valorIMC;

    public IMCCalc( double peso, double altura ){
         double altura_dobrada = Math.pow(altura,2);
         this.valorIMC = peso / altura_dobrada;
    }

    public double getValorIMC() {
        return valorIMC;
    }
}
