import rest.EtsisiRest;
import rest.model.NPM;
import rest.EtsisiService;
import rest.model.Noticia;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * Created by lfmingo on 10/5/17.
 */
public class Principal {

    public static void main(String[] args) throws IOException {
        System.out.println("...");


        EtsisiService service = EtsisiRest.getInstance();

        Call<NPM> salida = service.listado();
        NPM npm = salida.execute().body();
        for(Noticia n:npm.noticias)
            System.out.println(n.name);


        /*Call<List<Noticia>> salida = service.listado();

        List<Noticia> npm = salida.execute().body();
        for(Noticia n:npm)
            System.out.println(n.name);
        */
    }
}
