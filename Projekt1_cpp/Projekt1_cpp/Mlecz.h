#pragma once
#include "Roslina.h"
class Mlecz :
    public Roslina
{
public:
    Mlecz(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void akcja()override;
    void rysowanie() override;
    string getNazwa()override;
};

