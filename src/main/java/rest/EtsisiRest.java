package rest;

import retrofit2.Retrofit;

/**
 * Created by lfmingo on 11/5/17.
 */
public class EtsisiRest {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.etsisi.upm.es")
            .addConverterFactory(new JsoupConverterFactory())
            .build();

    private static EtsisiService service;

    public static EtsisiService getInstance() {
        if (service == null)
            service = retrofit.create(EtsisiService.class);
        return service;
    }


}
