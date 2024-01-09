package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;

public class Antylopa extends Zwierze {

    public Antylopa(Pair pole, Swiat swiat) {
        super(4, 4, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Antylopa(pole, swiat);
    }

    @Override
    public void akcja() {
        Pair obecnePole = pole;
        Pair nowePole = losujPole(false);
        if (nowePole.first() != -1) {
            pole = nowePole;
            Pair dodatkowePole = losujPole(false);
            pole = obecnePole;
            if (dodatkowePole.first() != -1 && dodatkowePole != pole)
                nowePole = dodatkowePole;
            ruszanie(nowePole);
        }

    }

    @Override
    public Color rysuj() {
        return Color.YELLOW.darker();
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        if (czyUcieka() == true)
            return "Brak";
        return super.rodzajKolizji(organizm);
    }

    @Override
    public String getNazwa() {
        return "antylopa";
    }

    private boolean czyUcieka() {
        int los = swiat.rand.nextInt(100);
        if (los <= 50)
            return true;
        return false;
    }
}
