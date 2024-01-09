#pragma once
#include "Roslina.h"
class Trawa :
    public Roslina
{
public:
    Trawa(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void rysowanie() override;
    string getNazwa()override;
};

