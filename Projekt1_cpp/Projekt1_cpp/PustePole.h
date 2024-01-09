#pragma once
#include "Organizm.h"
class PustePole :
    public Organizm
{
public:
	PustePole();
	void akcja()override {}
	void kolizja(Organizm* organizm)override {}
	void rysowanie()override;
	Organizm* potomek(pair<int, int>pole) override;
	string getNazwa()override;

};

