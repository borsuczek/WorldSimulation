package projekt2;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
import java.io.IOException;

import projekt2.zwierzeta.*;
import projekt2.rosliny.*;

public class Swiat {
    private int x, y;
    private String komentarze;
    public static final int ILOSC_ORGANIZMOW = 11;
    private Organizm swiat[][];
    private Vector<Organizm> barszcze = new Vector<Organizm>();
    public static final Random rand = new Random();

    public Swiat(int x, int y) {
        this.x = x;
        this.y = y;
        swiat = new Organizm[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                swiat[i][j] = new PustePole();
            }
        }
    }

    public Swiat() {
    }

    public void wykonajTure() {
        komentarze = "";
        Vector<Organizm> organizmy = new Vector<Organizm>();
        barszcze.removeAll(barszcze);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (swiat[i][j].getNazwa() != "puste") {
                    swiat[i][j].setWiek(swiat[i][j].getWiek() + 1);
                    organizmy.add(swiat[i][j]);
                }
                if (swiat[i][j].getNazwa() == "barszcz sosnowskiego") {
                    barszcze.add(swiat[i][j]);
                }
            }
        }
        for (int i = 0; i < organizmy.size(); i++) {
            for (int j = 0; j < organizmy.size() - 1; j++) {
                if (!komparator(organizmy.get(j), organizmy.get(j + 1))) {
                    Collections.swap(organizmy, j, j + 1);
                }
            }
        }
        for (int i = 0; i < organizmy.size(); i++) {
            if (organizmy.get(i).isZyje() == true)
                organizmy.get(i).akcja();
        }
    }

    private boolean komparator(Organizm a, Organizm b) {
        if (a.getIncjatywa() > b.getIncjatywa()) {
            return true;
        } else if (a.getIncjatywa() == b.getIncjatywa()) {
            if (a.getWiek() >= b.getWiek())
                return true;
        }
        return false;
    }

    public void generujSwiat() {
        swiat = new Organizm[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                swiat[i][j] = new PustePole();
            }
        }
        Pair czlowiek = new Pair(rand.nextInt(x), rand.nextInt(y));
        swiat[czlowiek.first()][czlowiek.second()] = new Czlowiek(czlowiek, this);
        int rozmiarPlanszy = x * y;
        for (int j = 0; j < ILOSC_ORGANIZMOW; j++) {
            int i = 0;
            int iloscOrganizmu = rand.nextInt(rozmiarPlanszy / ILOSC_ORGANIZMOW) + 1;
            while (i < iloscOrganizmu) {
                Pair organizm = new Pair(rand.nextInt(x), rand.nextInt(y));
                if (swiat[organizm.first()][organizm.second()].getNazwa() == "puste") {
                    if (j == 0)
                        swiat[organizm.first()][organizm.second()] = new Owca(organizm, this);
                    else if (j == 1)
                        swiat[organizm.first()][organizm.second()] = new Wilk(organizm, this);
                    else if (j == 2)
                        swiat[organizm.first()][organizm.second()] = new Lis(organizm, this);
                    else if (j == 3)
                        swiat[organizm.first()][organizm.second()] = new Zolw(organizm, this);
                    else if (j == 4)
                        swiat[organizm.first()][organizm.second()] = new Antylopa(organizm, this);
                    else if (j == 5)
                        swiat[organizm.first()][organizm.second()] = new Trawa(organizm, this);
                    else if (j == 6)
                        swiat[organizm.first()][organizm.second()] = new Mlecz(organizm, this);
                    else if (j == 7)
                        swiat[organizm.first()][organizm.second()] = new Guarana(organizm, this);
                    else if (j == 8)
                        swiat[organizm.first()][organizm.second()] = new WilczeJagody(organizm, this);
                    else if (j == 9)
                        swiat[organizm.first()][organizm.second()] = new BarszczSosnowskiego(organizm, this);
                    else if (j == 10)
                        swiat[organizm.first()][organizm.second()] = new CyberOwca(organizm, this);
                    i++;
                }
            }
        }
    }


    public String zapiszSwiat() {
        try {
            File file = new File("zapis.txt");
            file.createNewFile();
            PrintWriter zapis = new PrintWriter(file);
            zapis.println(x + " " + y);
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    if (swiat[i][j].getNazwa() != "puste") {
                        String nazwa = swiat[i][j].getNazwa();
                        nazwa = nazwa.replaceAll(" ", "");
                        zapis.println(nazwa);
                        zapis.println(swiat[i][j].getSila() + " " + swiat[i][j].getWiek() + " " + swiat[i][j].getPole().first() + " " + swiat[i][j].getPole().second());
                        if (nazwa == "czlowiek")
                            zapis.println(Czlowiek.getCzasUmiejetnosci());

                    }
                }
            }
            zapis.close();
            return "Swiat zapisany";
        } catch (IOException e) {
            System.out.println("ERROR");
            return "Wystapil blad, nie udalo sie zapisac swiata";
        }
    }

    public void wczytajSwiat() {
        try {
            FileReader file = new FileReader("zapis.txt");
            Scanner scanner = new Scanner(file);
            String organizm;
            int sila, wiek, pole1, pole2;
            x = scanner.nextInt();
            y = scanner.nextInt();
            scanner.nextLine();
            swiat = new Organizm[x][y];
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    swiat[i][j] = new PustePole();
                }
            }
            while (scanner.hasNextLine()) {
                organizm = scanner.nextLine();

                sila = scanner.nextInt();
                wiek = scanner.nextInt();
                pole1 = scanner.nextInt();
                pole2 = scanner.nextInt();
                scanner.nextLine();
                Pair pole = new Pair(pole1, pole2);
                if (organizm.equals("owca")) swiat[pole1][pole2] = new Owca(pole, this);
                else if (organizm.equals("cyber-owca")) swiat[pole1][pole2] = new CyberOwca(pole, this);
                else if (organizm.equals("wilk")) swiat[pole1][pole2] = new Wilk(pole, this);
                else if (organizm.equals("lis")) swiat[pole1][pole2] = new Lis(pole, this);
                else if (organizm.equals("zolw")) swiat[pole1][pole2] = new Zolw(pole, this);
                else if (organizm.equals("antylopa")) swiat[pole1][pole2] = new Antylopa(pole, this);
                else if (organizm.equals("trawa")) swiat[pole1][pole2] = new Trawa(pole, this);
                else if (organizm.equals("mlecz")) swiat[pole1][pole2] = new Mlecz(pole, this);
                else if (organizm.equals("guarana")) swiat[pole1][pole2] = new Guarana(pole, this);
                else if (organizm.equals("wilczejagody")) swiat[pole1][pole2] = new WilczeJagody(pole, this);
                else if (organizm.equals("barszczsosnowskiego"))
                    swiat[pole1][pole2] = new BarszczSosnowskiego(pole, this);
                else if (organizm.equals("czlowiek")) {
                    swiat[pole1][pole2] = new Czlowiek(pole, this);
                    int czasUmiejetnosci;
                    czasUmiejetnosci = scanner.nextInt();
                    scanner.nextLine();
                    Czlowiek.setCzasUmiejetnosci(czasUmiejetnosci);
                }
                swiat[pole1][pole2].setSila(sila);
                swiat[pole1][pole2].setWiek(wiek);
            }
            scanner.close();
        } catch (IOException e) {System.out.println("ERROR");}

    }

    public String getKomentarze() {
        return komentarze;
    }

    public void setKomentarze(String komentarze) {
        this.komentarze = komentarze;
    }

    public Vector<Organizm> getBarszcze() {
        return barszcze;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Organizm[][] getSwiat() {
        return swiat;
    }
}
