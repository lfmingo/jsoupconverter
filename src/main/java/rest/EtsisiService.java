package rest;

import io.reactivex.Observable;
import rest.model.NPM;
import retrofit2.http.GET;

/**
 * Created by lfmingo on 10/5/17.
 */
public interface EtsisiService {

    @GET("/eventos/marcador/futbol/2016_17/primera/jornada_37/")
    Observable<NPM> listado();
}
