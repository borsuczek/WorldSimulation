#pragma once
#include "Organizm.h"

class Roslina :
    public Organizm
{
public:
	Roslina(int sila, int wiek, pair<int, int>pole, Swiat* swiat);
	void akcja()override;
	void kolizja(Organizm* organizm) override {};
protected:
	void rozprzestrzenianie();
private:
	bool czySieje();
};

