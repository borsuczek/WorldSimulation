#pragma once
#include "Roslina.h"
class Guarana :
    public Roslina
{
public:
    Guarana(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void rysowanie() override;
    string rodzajKolizji(Organizm* organizm)override;
    string getNazwa()override;
};

