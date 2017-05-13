package rest;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rest.model.NPM;
import rest.model.Noticia;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by lfmingo on 10/5/17.
 */
public interface EtsisiService {

    @GET("/")
    Observable<NPM> listado();
}
