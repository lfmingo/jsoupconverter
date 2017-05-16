package rest.model;

import jsoupannotations.interfaces.*;

import java.util.List;

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

    @Text("h3.generico")
    public String status;
}