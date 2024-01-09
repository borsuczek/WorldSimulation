#pragma once
#include "Zwierze.h"
class Owca :
    public Zwierze
{
public:
    Owca(pair<int, int> pole, Swiat* swiat);
    Organizm* potomek(pair<int, int>pole) override;
    void rysowanie() override;
    string getNazwa()override;
};

