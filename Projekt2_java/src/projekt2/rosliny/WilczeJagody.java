package projekt2.rosliny;

import projekt2.*;

import java.awt.*;

public class WilczeJagody extends Roslina {
    public WilczeJagody(Pair pole, Swiat swiat) {
        super(99, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new WilczeJagody(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.BLUE.darker();
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        super.rodzajKolizji(organizm);
        return "Ginie";
    }

    @Override
    public String getNazwa() {
        return "wilcze jagody";
    }
}
