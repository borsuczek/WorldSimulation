package projekt2;

import projekt2.zwierzeta.Czlowiek;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.util.Random;
import java.lang.String;

public abstract class Organizm {
    protected boolean zwierze, zyje;
    protected int sila, incjatywa, wiek;
    protected Swiat swiat;
    protected Pair pole;

    public abstract void akcja();

    public abstract void kolizja(Organizm organizm);

    public abstract Color rysuj();

    public abstract Organizm potomek(Pair pole);

    public abstract String getNazwa();

    public Organizm(int sila, int incjatywa, int wiek, boolean zwierze, Pair pole, Swiat swiat, boolean zyje) {
        this.zyje = zyje;
        this.sila = sila;
        this.incjatywa = incjatywa;
        this.wiek = wiek;
        this.zwierze = zwierze;
        this.pole = pole;
        this.swiat = swiat;
    }

    public Pair losujPole(boolean czyWolne) {
        Vector<Pair> sasiednie = new Vector<Pair>();
        Pair nastepne = new Pair(-1, -1);
        if (pole.first() != 0) {
            if (czyWolne == true) {
                if (swiat.getSwiat()[pole.first() - 1][pole.second()].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second()));
                }
            } else {
                sasiednie.add(new Pair(pole.first() - 1, pole.second()));
            }
        }
        if (pole.first() != swiat.getX() - 1) {
            if (czyWolne == true) {
                if (swiat.getSwiat()[pole.first() + 1][pole.second()].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second()));
                }
            } else {
                sasiednie.add(new Pair(pole.first() + 1, pole.second()));
            }
        }
        if (pole.second() != swiat.getY() - 1) {
            if (czyWolne == true) {
                if (swiat.getSwiat()[pole.first()][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first(), pole.second() + 1));
                }
            } else {
                sasiednie.add(new Pair(pole.first(), pole.second() + 1));
            }
        }
        if (pole.second() != 0) {
            if (czyWolne == true) {
                if (swiat.getSwiat()[pole.first()][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first(), pole.second() - 1));
                }
            } else {
                sasiednie.add(new Pair(pole.first(), pole.second() - 1));
            }
        }
        if (sasiednie.size() == 0) {
            return nastepne;
        }
        return sasiednie.get(swiat.rand.nextInt(sasiednie.size()));
    }

    public String rodzajKolizji(Organizm organizm) {
        if (organizm.getSila() >= sila)
            return "Wygrywa";
        return "Przegrywa";
    }

    public void ruch(Pair nowePole) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " poruszyl(a) sie z pola " + "( " + (getPole().first() + 1) + "," + (getPole().second() + 1) + " ) na pole ( " + (nowePole.first() + 1) + "," + (nowePole.second() + 1) + " )");
        swiat.getSwiat()[nowePole.first()][nowePole.second()] = swiat.getSwiat()[pole.first()][pole.second()];
        swiat.getSwiat()[pole.first()][pole.second()] = new PustePole();
        swiat.getSwiat()[nowePole.first()][nowePole.second()].setPole(nowePole);
    }

    protected void smierc(Pair pole) {
        swiat.getSwiat()[pole.first()][pole.second()].setZyje(false);
        swiat.getSwiat()[pole.first()][pole.second()] = new PustePole();
    }

    public void zabijany(Organizm organizm) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (organizm.getPole().second() + 1) + " ) zabija zwierze " + getNazwa() + " ( " + (pole.first() + 1) + "," + (pole.second() + 1) + " )");
        smierc(pole);
    }

    public int getSila() {
        return sila;
    }

    public int getIncjatywa() {
        return incjatywa;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public boolean isZyje() {
        return zyje;
    }

    public void setZyje(boolean zyje) {
        this.zyje = zyje;
    }

    public boolean isZwierze() {
        return zwierze;
    }

    public Pair getPole() {
        return pole;
    }

    public void setPole(Pair pole) {
        this.pole = pole;
    }
}
