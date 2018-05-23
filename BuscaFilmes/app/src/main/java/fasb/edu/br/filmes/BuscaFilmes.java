package fasb.edu.br.filmes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BuscaFilmes {

    //escrever sobre a API aqui
    private String site = "";
    private URL url;

    public BuscaFilmes() {
        try {
            url = new URL(this.site);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<Filme> getFilmes( String filtro ) {
        return null;
    }
}
