package rest;

import jsoupannotations.main.JsoupProcessor;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import rest.model.NPM;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by lfmingo on 11/5/17.
 */
public class JsoupConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsoupConverter(type);
    }
}

class JsoupConverter implements Converter<ResponseBody, Object> {
    private final Type type;

    public JsoupConverter(Type t) {
        type = t;
    }

    @Override
    public Object convert(ResponseBody responseBody) throws IOException {
        try {
            String html = responseBody.string();
            Element element = Jsoup.parse(html);
            Object parse = JsoupProcessor.from(element, (Class<?>) type);
            return parse;
        } catch (Exception e) {
            throw new IOException("Failed to parse JSoup", e);
        }
    }
}
