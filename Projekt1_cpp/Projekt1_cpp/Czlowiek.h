#pragma once
#include "Zwierze.h"

class Czlowiek :
    public Zwierze
{
public:
    Czlowiek(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void akcja() override;
    string rodzajKolizji(Organizm* organizm) override;
    void rysowanie() override;
    string getNazwa()override;
    void kolizja(Organizm* organizm)override;
    static void setKierunek(pair<int, int> kierunek);
    static pair<int, int> getKierunek();
    static void setCzasUmiejetnosci(int czasUmiejetnosci);
    static int getCzasUmiejetnosci();
    static bool isAktywacja();
    static void setAktywacja(bool aktywacja);
private:
    static pair<int, int>kierunek;
    static int czasUmiejetnosci;
    static bool aktywacja;
};

