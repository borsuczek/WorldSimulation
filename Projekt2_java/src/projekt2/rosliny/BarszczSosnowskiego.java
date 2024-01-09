package projekt2.rosliny;

import projekt2.*;

import java.awt.*;

public class BarszczSosnowskiego extends Roslina {

    public BarszczSosnowskiego(Pair pole, Swiat swiat) {
        super(10, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new BarszczSosnowskiego(pole, swiat);
    }

    @Override
    public void akcja() {
        zabijanie();
        rozprzestrzenianie();
    }

    private void zabijanie() {
        if (pole.first() != 0) {
            if (swiat.getSwiat()[pole.first() - 1][pole.second()].isZwierze() == true) {
                swiat.getSwiat()[pole.first() - 1][pole.second()].zabijany(this);
            }
        }
        if (pole.first() != swiat.getX() - 1) {
            if (swiat.getSwiat()[pole.first() + 1][pole.second()].isZwierze() == true) {
                swiat.getSwiat()[pole.first() + 1][pole.second()].zabijany(this);
            }
        }
        if (pole.second() != swiat.getY() - 1) {
            if (swiat.getSwiat()[pole.first()][pole.second() + 1].isZwierze() == true) {
                swiat.getSwiat()[pole.first()][pole.second() + 1].zabijany(this);
            }
        }
        if (pole.second() != 0) {
            if (swiat.getSwiat()[pole.first()][pole.second() - 1].isZwierze() == true) {
                swiat.getSwiat()[pole.first()][pole.second() - 1].zabijany(this);
            }
        }
    }

    @Override
    public Color rysuj() {
        return Color.CYAN;
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        super.rodzajKolizji(organizm);
        return "Ginie";
    }

    @Override
    public String getNazwa() {
        return "barszcz sosnowskiego";
    }
}
