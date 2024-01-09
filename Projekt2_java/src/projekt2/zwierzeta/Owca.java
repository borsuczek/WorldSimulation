package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(Pair pole, Swiat swiat) {
        super(4, 4, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Owca(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public String getNazwa() {
        return "owca";
    }
}
