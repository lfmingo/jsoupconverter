package rest;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by lfmingo on 11/5/17.
 */
public class EtsisiRest {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.etsisi.upm.es")
            .addConverterFactory(new JsoupConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private static EtsisiService service;

    public static EtsisiService getInstance() {
        if (service == null)
            service = retrofit.create(EtsisiService.class);
        return service;
    }


}
