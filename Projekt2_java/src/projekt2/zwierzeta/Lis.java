package projekt2.zwierzeta;

import projekt2.*;

import java.awt.*;
import java.util.Vector;

public class Lis extends Zwierze {

    public Lis(Pair pole, Swiat swiat) {
        super(3, 7, 0, pole, swiat);
    }

    @Override
    public Organizm potomek(Pair pole) {
        return new Lis(pole, swiat);
    }

    @Override
    public Color rysuj() {
        return Color.PINK;
    }

    @Override
    public Pair losujPole(boolean czyWolne) {
        Vector<Pair> sasiednie = new Vector<Pair>();
        if (pole.first() != 0) {
            if (swiat.getSwiat()[pole.first() - 1][pole.second()].getSila() <= sila) {
                sasiednie.add(new Pair(pole.first() - 1, pole.second()));
            }
        }
        if (pole.first() != swiat.getX() - 1) {
            if (swiat.getSwiat()[pole.first() + 1][pole.second()].getSila() <= sila) {
                sasiednie.add(new Pair(pole.first() + 1, pole.second()));
            }
        }
        if (pole.second() != swiat.getY() - 1) {
            if (swiat.getSwiat()[pole.first()][pole.second() + 1].getSila() <= sila) {
                sasiednie.add(new Pair(pole.first(), pole.second() + 1));
            }
        }
        if (pole.second() != 0) {
            if (swiat.getSwiat()[pole.first()][pole.second() - 1].getSila() <= sila) {
                sasiednie.add(new Pair(pole.first(), pole.second() - 1));
            }
        }
        if (sasiednie.size() == 0)
            return new Pair(-1, -1);
        return sasiednie.get(swiat.rand.nextInt(sasiednie.size()));
    }

    @Override
    public String getNazwa() {
        return "lis";
    }
}
