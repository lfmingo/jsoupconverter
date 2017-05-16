package rest;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import rest.model.OfflineCacheInterceptor;
import rest.model.OnlineCacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lfmingo on 11/5/17.
 */
public class EtsisiRest {


    private final static int CACHE_SIZE_BYTES = 1024*1024*10;

    private static OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .cache(new Cache(new File("."),CACHE_SIZE_BYTES))
            .addNetworkInterceptor(new OnlineCacheInterceptor())
            .addInterceptor(new OfflineCacheInterceptor( true)) // TODO: check inet
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
            .addInterceptor( chain -> {
                Response response = chain.proceed(chain.request());
                if (response.cacheControl() != null)
                    Logger.getLogger(getInstance().getClass().getName()).log(Level.WARNING, "Response from CACHE");
                if (response.networkResponse() != null)
                    Logger.getLogger(getInstance().getClass().getName()).log(Level.WARNING, "Response from NETWORK");

                return response;
            })
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.marca.com")
            .addConverterFactory(new JsoupConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();

    private static EtsisiService service;

    public static EtsisiService getInstance() {
        if (service == null)
            service = retrofit.create(EtsisiService.class);
        return service;
    }


}
