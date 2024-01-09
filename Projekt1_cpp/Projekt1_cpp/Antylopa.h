#pragma once
#include "Zwierze.h"
class Antylopa :
    public Zwierze
{
public:
    Antylopa(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void akcja()override;
    void rysowanie() override;
    string rodzajKolizji(Organizm* organizm)override;
    string getNazwa()override;
private:
    bool czyUcieka();
};

