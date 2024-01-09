#pragma once
#include "Roslina.h"
class BarszczSosnowskiego :
    public Roslina
{
public:
    BarszczSosnowskiego(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void akcja() override;
    void rysowanie() override;
    string rodzajKolizji(Organizm* organizm)override;
    string getNazwa()override;
private:
    void zabija();
};

