import io.reactivex.Observable;
import rest.EtsisiRest;
import rest.model.Goleador;
import rest.model.NPM;
import rest.interfaces.EtsisiService;

import java.io.IOException;

/**
 * Created by lfmingo on 10/5/17.
 */
public class Principal {

    public static void main(String[] args) throws IOException {
        System.out.println("...");


        EtsisiService service = EtsisiRest.getInstance();

        Observable<NPM> salida = service.listado(38);
        salida.flatMapIterable(x -> x.partidos)
                .subscribe( x -> {
                    System.out.println(x.local + " " + x.goals_local + " : " + x.goals_visit + " " + x.visit + " --> " + x.status);
                    for(Goleador goleador:x.goleadores)
                        System.out.println(goleador.name);
                }, y -> {
                    System.out.println(y.getMessage());
                    y.printStackTrace();
                });

    }
}
