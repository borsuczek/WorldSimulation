#include "Zwierze.h"

Zwierze::Zwierze(int sila, int incjatywa, int wiek, pair<int, int>pole, Swiat* swiat) 
	:Organizm(sila, incjatywa, wiek, true, pole, swiat, true){}

void Zwierze::akcja() {
	pair<int, int> nowePole = losujPole(false);
	if (nowePole.first != -1) {
		if (swiat->getSwiat()[nowePole.first][nowePole.second]->getNazwa() == "puste") {
			ruch(nowePole);
		}
		else
			kolizja(swiat->getSwiat()[nowePole.first][nowePole.second]);
	}
}

void Zwierze::kolizja(Organizm* organizm) {
	if (organizm->getNazwa() == getNazwa()) {
		rozmnazanie(organizm->getPole());
	}
	else {	
		cout << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1<< " ) zostal zaatakowany(a) przez " << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " )" << endl;
		string rodzaj = organizm->rodzajKolizji(this);
		if (rodzaj == "Brak") {
			pair<int, int> poleUcieczki = organizm->losujPole(true);
			pair<int, int> obecnePole = organizm->getPole();
			cout << organizm->getNazwa() << " ucieka przed " << getNazwa() << " ( " << pole.first + 1 << "," << pole.second + 1 << " )" << endl;
			if (poleUcieczki.first != -1) {
				organizm->ruch(poleUcieczki);
				ruch(obecnePole);
			}
		}
		else if (rodzaj == "Przegrywa") {
			cout << getNazwa() << " ( " << pole.first + 1 << "," << pole.second + 1 << " ) przegrywa, " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " ) wygrywa" << endl;
			swiat->getSwiat()[pole.first][pole.second]->setZyje(false);
			swiat->getSwiat()[pole.first][pole.second] = new PustePole();
		}
		else if (rodzaj == "Wygrywa") {
			cout << getNazwa() << " ( " << pole.first + 1 << "," << pole.second + 1 << " ) wygrywa, " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " ) przegrywa" << endl;
			swiat->getSwiat()[organizm->getPole().first][organizm->getPole().second]->setZyje(false);
			ruch(organizm->getPole());
		}
		else if (rodzaj == "Odepchniety") {
			cout << getNazwa() << " ( " << pole.first + 1 << "," << pole.second + 1 << " ) zostal(a) odepchniety(a) przez " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " )" << endl;
		}
	}
}

void Zwierze::rozmnazanie(pair<int, int> innyZwierz) {
	pair<int, int> polePotomka = losujPolePotomka(innyZwierz);
	if (polePotomka.first != -1) {
		cout << "Pojawil(a) sie nowy(a) " << getNazwa() << " na polu ( " << polePotomka.first + 1 << "," << polePotomka.second + 1 << " )" << endl;
		swiat->getSwiat()[polePotomka.first][polePotomka.second] = potomek(polePotomka);
	}
}

pair<int, int> Zwierze::losujPolePotomka(pair<int, int> innyZwierz) {
	vector<pair<int, int> >sasiednie;
	if (pole.first - 1 == innyZwierz.first) {
		if (pole.second != 0) {
			if (swiat->getSwiat()[pole.first][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first, pole.second - 1));
			}
			if (swiat->getSwiat()[pole.first - 1][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second - 1));
			}
		}
		if (pole.second != swiat->getY() - 1) {
			if (swiat->getSwiat()[pole.first][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first, pole.second + 1));
			}
			if (swiat->getSwiat()[pole.first - 1][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second + 1));
			}
		}
	}
	if (pole.first + 1 == innyZwierz.first) {
		if (pole.second != 0) {
			if (swiat->getSwiat()[pole.first][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first, pole.second - 1));
			}
			if (swiat->getSwiat()[pole.first + 1][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second - 1));
			}
		}
		if (pole.second != swiat->getY() - 1) {
			if (swiat->getSwiat()[pole.first][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first, pole.second + 1));
			}
			if (swiat->getSwiat()[pole.first + 1][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second + 1));
			}
		}
	}
	if (pole.second - 1 == innyZwierz.second) {
		if (pole.first != 0) {
			if (swiat->getSwiat()[pole.first - 1][pole.second]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second));
			}
			if (swiat->getSwiat()[pole.first - 1][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second - 1));
			}
		}
		if (pole.first != swiat->getX() - 1) {
			if (swiat->getSwiat()[pole.first + 1][pole.second]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second));
			}
			if (swiat->getSwiat()[pole.first + 1][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second - 1));
			}
		}
	}
	if (pole.second + 1 == innyZwierz.second) {
		if (pole.first != 0) {
			if (swiat->getSwiat()[pole.first - 1][pole.second]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second));
			}
			if (swiat->getSwiat()[pole.first - 1][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second + 1));
			}
		}
		if (pole.first != swiat->getX() - 1) {
			if (swiat->getSwiat()[pole.first + 1][pole.second]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second));
			}
			if (swiat->getSwiat()[pole.first + 1][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second + 1));
			}
		}
	}
	if (sasiednie.size() == 0)
		return make_pair(-1, -1);
	return sasiednie[rand() % sasiednie.size()];
}