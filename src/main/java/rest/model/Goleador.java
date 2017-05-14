package rest.model;

import jsoupannotations.interfaces.Selector;
import jsoupannotations.interfaces.Text;

/**
 * Created by lfmingo on 14/5/17.
 */
@Selector("ul.lista-goles > li")
public class Goleador {

    @Text("li")
    public String name;
}
