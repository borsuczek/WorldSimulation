#pragma once
#include "Zwierze.h"
class Lis :
    public Zwierze
{
public:
    Lis(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void rysowanie() override;
    pair<int, int> losujPole(bool czyWolne) override;
    string getNazwa()override;
};

