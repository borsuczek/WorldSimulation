package projekt2;

import java.awt.*;

public class PustePole extends Organizm {
    public PustePole() {
        super(-1, -1, -1, false, new Pair(-1, -1), null, false);
    }

    public Organizm potomek(Pair pole) {
        return null;
    }

    @Override
    public void akcja() {
    }

    @Override
    public void kolizja(Organizm organizm) {
    }

    @Override
    public Color rysuj() {
        return Color.WHITE;
    }

    @Override
    public String getNazwa() {
        return "puste";
    }
}
