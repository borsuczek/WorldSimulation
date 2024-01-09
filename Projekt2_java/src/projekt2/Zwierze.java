package projekt2;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Zwierze extends Organizm{

    public Zwierze(int sila, int incjatywa, int wiek, Pair pole, Swiat swiat) {
        super(sila, incjatywa, wiek, true, pole, swiat, true);
    }

    @Override
    public void akcja() {
        Pair nowePole = losujPole(false);
        ruszanie(nowePole);
    }

    protected void ruszanie(Pair nowePole) {
        if (nowePole.first() != -1) {
            if (swiat.getSwiat()[nowePole.first()][nowePole.second()].getNazwa() == "puste") {
                ruch(nowePole);
            } else
                kolizja(swiat.getSwiat()[nowePole.first()][nowePole.second()]);
        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (organizm.getNazwa() == getNazwa())
            rozmnazanie(organizm.getPole());
        else {
            String rodzaj = organizm.rodzajKolizji(this);
            if (rodzaj == "Zjada")
                zjada(organizm);
            else if (rodzaj == "Ginie")
                ginie(organizm);
            else {
                swiat.setKomentarze(swiat.getKomentarze() + "\n" + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (organizm.getPole().second() + 1) + " ) zostal zaatakowany(a) przez " + getNazwa() + " ( " + (getPole().first() + 1) + "," + (getPole().second() + 1) + " )");
                if (rodzaj == "Brak")
                    brak(organizm);
                else if (rodzaj == "Przegrywa")
                    przegrywa(organizm);
                else if (rodzaj == "Wygrywa")
                    wygrywa(organizm);
                else if (rodzaj == "Odepchniety")
                    odepchniety(organizm);
            }
        }
    }

    protected void zjada(Organizm organizm) {
        ruch(organizm.getPole());
    }

    protected void ginie(Organizm organizm) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " ( " + (pole.first() + 1) + "," + (pole.second() + 1) + " ) ginie");
        smierc(pole);
    }

    protected void przegrywa(Organizm organizm) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " ( " + (pole.first() + 1) + "," + (pole.second() + 1) + " ) przegrywa, " + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (organizm.getPole().second() + 1) + " ) wygrywa");
        smierc(pole);
    }

    protected void wygrywa(Organizm organizm) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " ( " + (pole.first() + 1) + "," + (pole.second() + 1) + " ) wygrywa, " + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (organizm.getPole().second() + 1) + " ) przegrywa");
        smierc(organizm.getPole());
        ruch(organizm.getPole());
    }

    protected void odepchniety(Organizm organizm) {
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " ( " + (pole.first() + 1) + "," + (pole.second() + 1) + " ) zostal(a) odepchniety(a) przez " + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (organizm.getPole().second() + 1) + " )");
    }

    protected void brak(Organizm organizm) {
        Pair poleUcieczki = organizm.losujPole(true);
        Pair obecnePole = organizm.getPole();
        swiat.setKomentarze(swiat.getKomentarze() + "\n" + organizm.getNazwa() + " ucieka przed " + getNazwa() + " ( " + (pole.first() + 1) + "," + (pole.second() + 1) + " )");
        if (poleUcieczki.first() != -1) {
            organizm.ruch(poleUcieczki);
            ruch(obecnePole);
        }
    }

    protected void rozmnazanie(Pair innyZwierz) {
        Pair polePotomka = losujPolePotomka(innyZwierz);
        if (polePotomka.first() != -1) {
            swiat.setKomentarze(swiat.getKomentarze() + "\n" + "Pojawil(a) sie nowy(a) " + getNazwa() + " na polu ( " + (polePotomka.first() + 1) + "," + (polePotomka.second() + 1) + " )");
            swiat.getSwiat()[polePotomka.first()][polePotomka.second()] = potomek(polePotomka);
        }
    }

    private Pair losujPolePotomka(Pair innyZwierz) {
        Vector<Pair> sasiednie = new Vector<Pair>();
        if (pole.first() - 1 == innyZwierz.first()) {
            if (pole.second() != 0) {
                if (swiat.getSwiat()[pole.first()][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first(), pole.second() - 1));
                }
                if (swiat.getSwiat()[pole.first() - 1][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second() - 1));
                }
            }
            if (pole.second() != swiat.getY() - 1) {
                if (swiat.getSwiat()[pole.first()][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first(), pole.second() + 1));
                }
                if (swiat.getSwiat()[pole.first() - 1][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second() + 1));
                }
            }
        }
        if (pole.first() + 1 == innyZwierz.first()) {
            if (pole.second() != 0) {
                if (swiat.getSwiat()[pole.first()][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first(), pole.second() - 1));
                }
                if (swiat.getSwiat()[pole.first() + 1][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second() - 1));
                }
            }
            if (pole.second() != swiat.getY() - 1) {
                if (swiat.getSwiat()[pole.first()][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first(), pole.second() + 1));
                }
                if (swiat.getSwiat()[pole.first() + 1][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second() + 1));
                }
            }
        }
        if (pole.second() - 1 == innyZwierz.second()) {
            if (pole.first() != 0) {
                if (swiat.getSwiat()[pole.first() - 1][pole.second()].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second()));
                }
                if (swiat.getSwiat()[pole.first() - 1][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second() - 1));
                }
            }
            if (pole.first() != swiat.getX() - 1) {
                if (swiat.getSwiat()[pole.first() + 1][pole.second()].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second()));
                }
                if (swiat.getSwiat()[pole.first() + 1][pole.second() - 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second() - 1));
                }
            }
        }
        if (pole.second() + 1 == innyZwierz.second()) {
            if (pole.first() != 0) {
                if (swiat.getSwiat()[pole.first() - 1][pole.second()].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second()));
                }
                if (swiat.getSwiat()[pole.first() - 1][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() - 1, pole.second() + 1));
                }
            }
            if (pole.first() != swiat.getX() - 1) {
                if (swiat.getSwiat()[pole.first() + 1][pole.second()].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second()));
                }
                if (swiat.getSwiat()[pole.first() + 1][pole.second() + 1].getNazwa() == "puste") {
                    sasiednie.add(new Pair(pole.first() + 1, pole.second() + 1));
                }
            }
        }
        if (sasiednie.size() == 0)
            return new Pair(-1, -1);
        return sasiednie.get(swiat.rand.nextInt(sasiednie.size()));
    }

    @Override
    public Color rysuj() {
        return Color.GREEN;
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
