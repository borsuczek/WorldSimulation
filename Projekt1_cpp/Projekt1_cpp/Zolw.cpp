#include "Zolw.h"

Zolw::Zolw(pair<int, int>pole, Swiat* swiat)
	:Zwierze(2, 1, 0, pole, swiat) {}

Organizm* Zolw::potomek(pair<int, int>pole) {
	return new Zolw(pole, swiat);
}

void Zolw::akcja() {
	if (czyRusza() == true) {
		pair<int, int> nowePole = losujPole(false);
		if (swiat->getSwiat()[nowePole.first][nowePole.second]->getNazwa() == "puste") {
			ruch(nowePole);
		}
		else
			kolizja(swiat->getSwiat()[nowePole.first][nowePole.second]);
	}
}

void Zolw::rysowanie() {
	cout << 'z';
}

string Zolw::rodzajKolizji(Organizm* organizm) {
	if (organizm->getSila() < 5)
		return "Odepchniety";
	else if (organizm->getSila() >= sila)
		return "Wygrywa";
	else
		return "Przegrywa";
}

string Zolw::getNazwa() {
	return "zolw";
}

bool Zolw::czyRusza() {
	int los = rand() % 100;
	if (los <= 75)
		return true;
	return false;
}