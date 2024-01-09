package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;
import java.util.Vector;
import java.lang.Math;

public class CyberOwca extends Zwierze {
    public CyberOwca(Pair pole, Swiat swiat) {
        super(11, 4, 0, pole, swiat);
    }

    @Override
    public void akcja() {
        Pair nowePole = szukaj();
        if (nowePole != null)
            ruszanie(nowePole);
        else
            super.akcja();
    }

    private Pair szukaj() {
        Vector<Organizm> barszcze = swiat.getBarszcze();
        if (barszcze.isEmpty())
            return null;
        int najblizej = odleglosc(barszcze.get(0));
        Pair barszcz = barszcze.get(0).getPole();
        for (int i = 1; i < barszcze.size(); i++) {
            if (barszcze.get(i).isZyje() && odleglosc(barszcze.get(i)) < najblizej) {
                najblizej = odleglosc(barszcze.get(i));
                barszcz = barszcze.get(i).getPole();
            }
        }
        return wspolrzedne(barszcz.first(), barszcz.second());
    }

    private int odleglosc(Organizm barszcz) {
        int x = Math.abs(barszcz.getPole().first() - pole.first());
        int y = Math.abs(barszcz.getPole().second() - pole.second());
        return x + y;
    }

    private Pair wspolrzedne(int x, int y) {
        int roznicaX = x - pole.first();
        int roznicaY = y - pole.second();

        if (roznicaY > 0) roznicaY = 1;
        else if (roznicaY < 0) roznicaY = -1;
        if (roznicaX > 0) roznicaX = 1;
        else if (roznicaX < 0) roznicaX = -1;

        if (roznicaX == 0 || roznicaY == 0) {
            return new Pair(pole.first() + roznicaX, pole.second() + roznicaY);
        }

        int los = swiat.rand.nextInt(2);
        if (los == 0)
            return new Pair(pole.first() + roznicaX, pole.second());
        else
            return new Pair(pole.first(), pole.second() + roznicaY);
    }

    @Override
    protected void ginie(Organizm organizm) {
        if (organizm.getNazwa() != "barszcz sosnowskiego")
            super.ginie(organizm);
        else
            zjada(organizm);
    }

    @Override
    public void zabijany(Organizm organizm) {
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new CyberOwca(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.GRAY;
    }

    @Override
    public String getNazwa() {
        return "cyber-owca";
    }
}
