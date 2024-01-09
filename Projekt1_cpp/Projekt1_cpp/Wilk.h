#pragma once
#include "Zwierze.h"
class Wilk :
    public Zwierze
{
public:
    Wilk(pair<int, int>pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void rysowanie() override;
    string getNazwa()override;
};

