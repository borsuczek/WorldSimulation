#pragma once
#include "Organizm.h"

class Zwierze :
    public Organizm
{
public:
	Zwierze(int sila, int incjatywa, int wiek, pair<int, int>pole, Swiat* swiat);
	void akcja()override;
protected:
	virtual void kolizja(Organizm* organizm)override;
	//virtual void ruch(pair<int, int> nowePole);
	virtual void rozmnazanie(pair<int, int> innyZwierz);
private:
	pair<int, int> losujPolePotomka(pair<int, int> innyZwierz);
};

