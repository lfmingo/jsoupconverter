package rest.model;

import jsoupannotations.interfaces.AfterBind;
import jsoupannotations.interfaces.Items;

import java.util.List;

public class NPM {
    @Items
    public List<Noticia> noticias;
}