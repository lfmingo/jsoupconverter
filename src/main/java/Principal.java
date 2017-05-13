import io.reactivex.Observable;
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

        Observable<NPM> salida = service.listado();
        salida.flatMapIterable(x -> x.noticias)
                .subscribe( x -> System.out.println(x.name));

    }
}
