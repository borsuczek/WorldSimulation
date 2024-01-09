package projekt2.rosliny;

import projekt2.*;

import java.awt.*;

public class Mlecz extends Roslina {

    public Mlecz(Pair pole, Swiat swiat) {
        super(0, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Mlecz(pole, swiat);
    }

    @Override
    public void akcja() {
        rozprzestrzenianie();
        rozprzestrzenianie();
        rozprzestrzenianie();
    }

    @Override
    public Color rysuj() {
        return Color.YELLOW.brighter();
    }

    @Override
    public String getNazwa() {
        return "mlecz";
    }
}
