# jsoupconverter

Integration of:
- Jsoup
- Jsoup-annotations 
- Retrofit2 
- RxJava2

using a custom **jsoup converter**.

```java
 EtsisiService service = EtsisiRest.getInstance();

        Observable<NPM> salida = service.listado();
        salida.flatMapIterable(x -> x.noticias)
                .subscribe( x -> System.out.println(x.name));
```
