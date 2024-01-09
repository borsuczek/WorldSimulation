#pragma once
#include "Organizm.h"
#include <iostream>
#include <algorithm>
#include <vector>
#include <fstream>
#include <string>

#define ILOSC_ORGANIZMOW 10

using namespace std;

class Swiat
{
public:
	Swiat(const int x, const int y);
	Swiat();
	void wykonajTure();
	void rysujSwiat();
	void generujSwiat();
	void zapiszSwiat();
	void wczytajSwiat();
	void rysujNaglowek();
	Organizm*** getSwiat();
	int getX();
	int getY();
private:
	int x;
	int y;
	bool komparator(Organizm* a, Organizm* b);
	Organizm*** swiat;
};

