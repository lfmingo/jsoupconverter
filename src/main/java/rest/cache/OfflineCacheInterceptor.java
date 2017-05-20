package rest.cache;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rest.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OfflineCacheInterceptor implements Interceptor {

    private boolean isOnline;

    public OfflineCacheInterceptor(boolean isOnline) {
        this.isOnline = isOnline;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!isOnline) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(Utils.OFFLINE_CACHE_TIME, TimeUnit.SECONDS)
                    .onlyIfCached()
                    .build();
            request = request.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build();
        }
        return chain.proceed(request);
    }
}