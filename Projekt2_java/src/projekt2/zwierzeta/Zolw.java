package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;

public class Zolw extends Zwierze {

    public Zolw(Pair pole, Swiat swiat) {
        super(2, 1, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Zolw(pole, swiat);
    }

    @Override
    public void akcja() {
        if (czyRusza() == true) {
            super.akcja();
        }
    }

    @Override
    public Color rysuj() {
        return Color.GREEN.darker();
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        if (organizm.getSila() < 5)
            return "Odepchniety";
        return super.rodzajKolizji(organizm);
    }

    @Override
    public String getNazwa() {
        return "zolw";
    }

    private boolean czyRusza() {
        int los = swiat.rand.nextInt(100);
        if (los <= 75)
            return true;
        return false;
    }
}
