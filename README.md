# jsoupconverter

Integration of:
- Jsoup
- Jsoup-annotations 
- Retrofit2 

using a custom *jsoup converter*.

```java
EtsisiService service = EtsisiRest.getInstance();

Call<NPM> salida = service.listado();
NPM npm = salida.execute().body();

for(Noticia n:npm.noticias)
    System.out.println(n.name);
```
