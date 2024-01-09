#pragma once
#include "Zwierze.h"
class Zolw :
    public Zwierze
{
public:
    Zolw(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void akcja()override;
    void rysowanie() override;
    string rodzajKolizji(Organizm* organizm)override;
    string getNazwa()override;
private:
    bool czyRusza();
};

