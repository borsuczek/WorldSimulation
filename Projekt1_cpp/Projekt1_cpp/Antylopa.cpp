#include "Antylopa.h"

Antylopa::Antylopa(pair<int, int>pole, Swiat* swiat)
	:Zwierze(4, 4, 0, pole, swiat) {}

Organizm* Antylopa::potomek(pair<int, int>pole) {
	return new Antylopa(pole, swiat);
}

void Antylopa::akcja() {
	pair<int, int> obecnePole = pole;
	pair<int, int> nowePole = losujPole(false);
	if (nowePole.first != -1) {
		pole = nowePole;
		pair<int, int> dodatkowePole = losujPole(false);
		pole = obecnePole;
		if (dodatkowePole.first != -1 && dodatkowePole != pole)
			nowePole = dodatkowePole;
		if (swiat->getSwiat()[nowePole.first][nowePole.second]->getNazwa() == "puste") {
			ruch(nowePole);
		}
		else
			kolizja(swiat->getSwiat()[nowePole.first][nowePole.second]);
	}
	
}

void Antylopa::rysowanie() {
	cout << 'a';
}

string Antylopa::rodzajKolizji(Organizm* organizm) {
	if (czyUcieka() == true) {
	/*pair<int, int> poleUcieczki= losujPole(true);
		if (poleUcieczki.first != -1) {
			cout << this->getNazwa() << " ucieka z pola " << "( " << this->getPole().first + 1 << "," << this->getPole().second + 1 << " ) na pole ( " << poleUcieczki.first + 1 << "," << poleUcieczki.second + 1 << " )" << endl;
			ruch(poleUcieczki);*/
			return "Brak";
		//}
	}
	if (organizm->getSila() >= sila)
		return "Wygrywa";
	return "Przegrywa";
}

string Antylopa::getNazwa() {
	return "antylopa";
}

bool Antylopa::czyUcieka() {
	int los = rand() % 100;
	if (los <= 50)
		return true;
	return false;
}