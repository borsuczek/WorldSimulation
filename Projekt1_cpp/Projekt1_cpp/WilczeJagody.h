#pragma once
#include "Roslina.h"
class WilczeJagody :
    public Roslina
{
public:
    WilczeJagody(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void rysowanie() override;
    string rodzajKolizji(Organizm* organizm)override;
    string getNazwa()override;
};

