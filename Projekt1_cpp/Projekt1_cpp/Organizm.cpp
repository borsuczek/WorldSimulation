#include "Organizm.h"

Organizm::Organizm(int sila, int incjatywa, int wiek, bool zwierze, pair<int, int>pole, Swiat* swiat, bool zyje) {
	this->zyje = zyje;
	this->sila = sila;
	this->incjatywa = incjatywa;
	this->wiek = wiek;
	this->zwierze = zwierze;
	this->pole = pole;
	this->swiat = swiat;
}

pair<int, int> Organizm::losujPole(bool czyWolne) {
	vector<pair<int,int> >sasiednie;
	if (pole.first != 0) {
		if (czyWolne == true) {
			if (swiat->getSwiat()[pole.first - 1][pole.second]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first - 1, pole.second));
			}
		}
		else {
			sasiednie.push_back(make_pair(pole.first - 1, pole.second));
		}
	}
	if (pole.first != swiat->getX()-1) {
		if (czyWolne == true) {
			if (swiat->getSwiat()[pole.first + 1][pole.second]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first + 1, pole.second));
			}
		}
		else {
			sasiednie.push_back(make_pair(pole.first + 1, pole.second));
		}
	}
	if (pole.second != swiat->getY() - 1) {
		if (czyWolne == true) {
			if (swiat->getSwiat()[pole.first][pole.second + 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first, pole.second + 1));
			}
		}
		else {
			sasiednie.push_back(make_pair(pole.first, pole.second + 1));
		}
	}
	if (pole.second != 0) {
		if (czyWolne == true) {
			if (swiat->getSwiat()[pole.first][pole.second - 1]->getNazwa() == "puste") {
				sasiednie.push_back(make_pair(pole.first, pole.second - 1));
			}
		}
		else {
			sasiednie.push_back(make_pair(pole.first, pole.second - 1));
		}
	}
	if (sasiednie.size() == 0)
		return make_pair(-1, -1);
	return sasiednie[rand() % sasiednie.size()];
}

string Organizm::rodzajKolizji(Organizm* organizm) {
	if (organizm->getSila() >= sila) {
		return "Wygrywa";
	}
	else
		return "Przegrywa";
}

void Organizm::ruch(pair<int, int> nowePole) {
	cout << getNazwa() << " poruszyl(a) sie z pola " << "( " << getPole().first + 1 << "," << getPole().second + 1 << " ) na pole ( " << nowePole.first + 1 << "," << nowePole.second + 1 << " )" << endl;
	swiat->getSwiat()[nowePole.first][nowePole.second] = swiat->getSwiat()[pole.first][pole.second];
	swiat->getSwiat()[pole.first][pole.second] = new PustePole();
	swiat->getSwiat()[nowePole.first][nowePole.second]->setPole(nowePole);
}

int Organizm::getSila() {
	return sila;
}

int Organizm::getIncjatywa() {
	return incjatywa;
}

void Organizm::setSila(int sila) {
	this->sila = sila;
}

int Organizm::getWiek() {
	return wiek;
}

void Organizm::setWiek(int wiek) {
	this->wiek = wiek;
}

bool Organizm::isZyje() {
	return zyje;
}

void Organizm::setZyje(bool zyje) {
	this->zyje = zyje;
}

bool Organizm::isZwierze() {
	return zwierze;
}

pair<int, int> Organizm::getPole() {
	return pole;
}

void Organizm::setPole(pair<int, int>pole) {
	this->pole = pole;
}