import io.reactivex.Observable;
import rest.EtsisiRest;
import rest.model.Goleador;
import rest.model.NPM;
import rest.EtsisiService;

import java.io.IOException;

/**
 * Created by lfmingo on 10/5/17.
 */
public class Principal {

    public static void main(String[] args) throws IOException {
        System.out.println("...");


        EtsisiService service = EtsisiRest.getInstance();

        Observable<NPM> salida = service.listado();
        salida.flatMapIterable(x -> x.partidos)
                .subscribe( x -> {
                    System.out.println(x.local + " " + x.goals_local + " : " + x.goals_visit + " " + x.visit + " --> " + x.status);
                    for(Goleador goleador:x.goleadores)
                        System.out.println(goleador.name);
                });

    }
}
