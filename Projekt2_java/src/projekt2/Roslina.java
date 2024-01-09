package projekt2;

import projekt2.Organizm;

import java.awt.*;

public class Roslina extends Organizm {
    public Roslina(int sila, int wiek, Pair pole, Swiat swiat) {
        super(sila, 0, wiek, false, pole, swiat, true);
    }

    @Override
    public void akcja() {
        rozprzestrzenianie();
    }

    protected void rozprzestrzenianie() {
        if (czySieje() == true) {
            Pair nowaRoslina = losujPole(true);
            if (nowaRoslina.first() != -1) {
                swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " rozprzestrzenil(a) sie na pole ( " + (nowaRoslina.first() + 1) + "," + (nowaRoslina.second() + 1) + " )");
                swiat.getSwiat()[nowaRoslina.first()][nowaRoslina.second()] = potomek(nowaRoslina);
            }
        }
    }

    private boolean czySieje() {
        int los = swiat.rand.nextInt(100);
        if (los <= 50)
            return true;
        return false;
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " ( " + (getPole().first() + 1) + "," + (getPole().second() + 1) + " ) zostaje zjedzony(a) przez " + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (organizm.getPole().second() + 1) + " ) ");
        smierc(pole);
        return "Zjada";
    }

    @Override
    public void kolizja(Organizm organizm) {
    }

    @Override
    public Color rysuj() {
        return Color.CYAN;
    }

    @Override
    public Organizm potomek(Pair pole) {
        return null;
    }

    @Override
    public String getNazwa() {
        return null;
    }
}
