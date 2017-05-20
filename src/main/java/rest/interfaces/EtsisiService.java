package rest.interfaces;

import io.reactivex.Observable;
import rest.model.NPM;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lfmingo on 10/5/17.
 */
public interface EtsisiService {

    @GET("/eventos/marcador/futbol/2016_17/primera/jornada_{id}/")
    Observable<NPM> listado(@Path("id") int id_jornada);
}
