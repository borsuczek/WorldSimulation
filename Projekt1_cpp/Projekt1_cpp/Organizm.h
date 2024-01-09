#pragma once
#include <iostream>
#include <vector>
#include <stdlib.h>

using namespace std;

class Swiat;
class PustePole;
class Organizm
{
public:
	Organizm(int sila, int incjatywa, int wiek, bool zwierze, pair<int, int>pole, Swiat* swiat, bool zyje);
	virtual void akcja() = 0;
	virtual void kolizja(Organizm* organizm) = 0;
	virtual void rysowanie() = 0;
	virtual Organizm* potomek(pair<int, int>pole) = 0;
	virtual string getNazwa() = 0;
	virtual string rodzajKolizji(Organizm* organizm);
	void ruch(pair<int, int> nowePole);
	int getSila();
	void setSila(int sila);
	int getIncjatywa();
	int getWiek();
	void setWiek(int wiek);
	bool isZyje();
	void setZyje(bool zyje);
	bool isZwierze();
	pair<int,int> getPole();
	void setPole(pair<int, int>pole);
	virtual pair<int, int> losujPole(bool czyWolne);
protected:
	bool zyje;
	int sila;
	int incjatywa;
	int wiek;
	bool zwierze;
	pair<int, int>pole;
	Swiat* swiat;
	
};
#include "Swiat.h"
#include "PustePole.h"