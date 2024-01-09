package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;

public class Czlowiek extends Zwierze {

    private static Pair kierunek = new Pair(-2, -2);
    private static int czasUmiejetnosci;
    private static boolean zyje = true;

    public Czlowiek(Pair pole, Swiat swiat) {
        super(5, 4, 0, pole, swiat);
        zyje = isZyje();
        czasUmiejetnosci = -5;
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new PustePole();
    }

    @Override
    public void akcja() {
        this.zyje = isZyje();
        if (kierunek.first() != -2) {
            Pair nowePole = new Pair(pole.first() + kierunek.first(), pole.second() + kierunek.second());
            if (nowePole.first() >= 0 && nowePole.second() >= 0 && nowePole.first() <= swiat.getX() - 1 && nowePole.second() <= swiat.getY() - 1) {
                ruszanie(nowePole);
            }
            kierunek.setPair(-2, -2);
        }
        if (czasUmiejetnosci != -5)
            czasUmiejetnosci--;
    }

    public static String stan() {
        if (Czlowiek.getCzasUmiejetnosci() == -5)
            Czlowiek.setCzasUmiejetnosci(5);
        if (zyje == false)
            return "Czlowiek nie zyje";
        else if (czasUmiejetnosci == 5)
            return "Umiejetnosc specjalna czlowieka wlaczona";
        else if (czasUmiejetnosci > 0)
            return "Umiejetnosc specjalna czlowieka nadal wlaczona, pozostało rund " + (czasUmiejetnosci - 1);
        else
            return "Umiejetnosc specjalna nie moze zostac aktywowana, pozostało rund " + (4 + czasUmiejetnosci);

    }

    @Override
    protected void przegrywa(Organizm organizm) {
        if (czasUmiejetnosci > 0) {
            Pair poleUcieczki = losujPole(true);
            swiat.setKomentarze(swiat.getKomentarze() + "\n" + getNazwa() + " ucieka przed " + organizm.getNazwa() + " ( " + (organizm.getPole().first() + 1) + "," + (getPole().second() + 1) + " )");
            if (poleUcieczki.first() != -1) {
                ruch(poleUcieczki);
            }
        } else
            super.przegrywa(organizm);
    }

    @Override
    protected void ginie(Organizm organizm) {
        if (czasUmiejetnosci > 0)
            zjada(organizm);
        else super.ginie(organizm);
    }

    @Override
    public String rodzajKolizji(Organizm organizm) {
        if (czasUmiejetnosci > 0 && organizm.getSila() >= sila)
            return "Brak";
        return super.rodzajKolizji(organizm);
    }

    @Override
    public Color rysuj() {
        return Color.MAGENTA.darker();
    }

    @Override
    public String getNazwa() {
        return "czlowiek";
    }

    public static void setKierunek(Pair _kierunek) {
        kierunek = _kierunek;
    }

    @Override
    public void setZyje(boolean zyje) {
        this.zyje = zyje;
        super.setZyje(zyje);
    }

    public static void setCzasUmiejetnosci(int _czasUmiejetnosci) {
        czasUmiejetnosci = _czasUmiejetnosci;
    }

    public static int getCzasUmiejetnosci() {
        return czasUmiejetnosci;
    }

}
