# jsoupconverter

Integration of:
- Jsoup
- Jsoup-annotations 
- Retrofit2 
- RxJava2

using a custom **jsoup converter**.

```java
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
```


Retrofit 2 configuration:
```java
private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.marca.com")
            .addConverterFactory(new JsoupConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

public interface EtsisiService {

    @GET("/eventos/marcador/futbol/2016_17/primera/jornada_37/")
    Observable<NPM> listado();
}
```

Jsoup annotation model:
```java
public class NPM {
    @Items
    public List<Partido> partidos;
}

@Selector("ul.partido")
public class Partido {

    @Text("strong.left-team")
    public String local;

    @Text("strong.right-team")
    public String visit;

    @Text("span.score.playing > span:eq(0)")
    public String goals_local;

    @Text("span.score.playing > span:eq(1)")
    public String goals_visit;

    @Items
    public List<Goleador> goleadores;

    @Text("h3.generico > a")
    public String status;
}

@Selector("ul.lista-goles > li")
public class Goleador {

    @Text("li")
    public String name;
}
```