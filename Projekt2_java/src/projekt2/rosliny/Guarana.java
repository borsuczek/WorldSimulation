package projekt2.rosliny;

import projekt2.*;

import java.awt.*;

public class Guarana extends Roslina {
    public Guarana(Pair pole, Swiat swiat) {
        super(0, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Guarana(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.RED.darker();
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        organizm.setSila(organizm.getSila() + 3);
        return super.rodzajKolizji(organizm);
    }

    @Override
    public String getNazwa() {
        return "guarana";
    }
}
