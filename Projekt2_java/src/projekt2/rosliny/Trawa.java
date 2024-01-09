package projekt2.rosliny;

import projekt2.*;

import java.awt.*;

public class Trawa extends Roslina {

    public Trawa(Pair pole, Swiat swiat) {
        super(0, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Trawa(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.GREEN.brighter();
    }

    @Override
    public String getNazwa() {
        return "trawa";
    }
}
