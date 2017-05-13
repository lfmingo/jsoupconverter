package rest.model;

import jsoupannotations.interfaces.*;

@Selector(".block-title")
public class Noticia {

    @Text("h2")
    public String name;
}