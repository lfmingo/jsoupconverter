package rest.cache;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;
import rest.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OnlineCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(Utils.ONLINE_CACHE_TIME, TimeUnit.SECONDS)
                .build();
        return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}