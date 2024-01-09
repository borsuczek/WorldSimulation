package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(Pair pole, Swiat swiat) {
        super(9, 5, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Wilk(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.BLACK;
    }

    @Override
    public String getNazwa() {
        return "wilk";
    }
}
