package rest;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import rest.model.OfflineCacheInterceptor;
import rest.model.OnlineCacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

/**
 * Created by lfmingo on 11/5/17.
 */
public class EtsisiRest {


    private final static int CACHE_SIZE_BYTES = 1024*1024*10;

    private static OkHttpClient.Builder builder = new OkHttpClient()
            .newBuilder()
            .cache(new Cache(new File("."),CACHE_SIZE_BYTES))
            .addNetworkInterceptor(new OnlineCacheInterceptor())
            .addInterceptor(new OfflineCacheInterceptor(true));

    private static OkHttpClient client = builder.build();

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
