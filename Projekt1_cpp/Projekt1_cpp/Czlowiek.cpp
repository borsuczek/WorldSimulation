#include "Czlowiek.h"

Czlowiek::Czlowiek(pair<int, int>pole, Swiat* swiat)
	:Zwierze(5, 4, 0, pole, swiat) {}

pair<int, int>Czlowiek::kierunek = make_pair(-2, -2);
int Czlowiek::czasUmiejetnosci = -5;
bool Czlowiek::aktywacja = false;

Organizm* Czlowiek::potomek(pair<int, int>pole) {
	return new PustePole();
}

void Czlowiek::akcja() {
	if (aktywacja == true){	
		if (czasUmiejetnosci == 5)
			cout << "Umiejetnosc specjalna czlowieka wlaczona" << endl;
		else if (czasUmiejetnosci > 0)
			cout << "Umiejetnosc specjalna czlowieka nadal wlaczona" << endl;
		else if (czasUmiejetnosci > -5)
			cout << "Umiejetnosc specjalna nie moze zostac aktywowana" << endl;
		aktywacja = false;
	}
	if (kierunek.first != -2) {
		pair<int, int> nowePole = make_pair(pole.first + kierunek.first, pole.second + kierunek.second);
		if (nowePole.first >= 0 && nowePole.second >= 0 && nowePole.first <= swiat->getX() - 1 && nowePole.second <= swiat->getY() - 1) {
			if (swiat->getSwiat()[nowePole.first][nowePole.second]->getNazwa() == "puste") {
				ruch(nowePole);
			}
			else
				kolizja(swiat->getSwiat()[nowePole.first][nowePole.second]);
		}
		kierunek.first = -2;
	}
	if (czasUmiejetnosci != -5)
		czasUmiejetnosci--;
}

void Czlowiek::kolizja(Organizm* organizm) {
	cout << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " ) zostal zaatakowany(a) przez " << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " )" << endl;
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
		if (czasUmiejetnosci > 0) {
			pair<int, int> poleUcieczki = losujPole(true);
			cout << getNazwa() << " ucieka przed " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << getPole().second + 1 << " )" << endl;
			if (poleUcieczki.first != -1) {
				ruch(poleUcieczki);
			}
		}
		else {
			cout << getNazwa() << " ( " << pole.first + 1 << "," << pole.second + 1 << " ) przegrywa, " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " ) wygrywa" << endl;
			swiat->getSwiat()[pole.first][pole.second]->setZyje(false);
			swiat->getSwiat()[pole.first][pole.second] = new PustePole();
		}
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

string Czlowiek::rodzajKolizji(Organizm* organizm) {
	if (organizm->getSila() >= sila) {
		if (czasUmiejetnosci > 0) {
			return "Brak";
		}
		return "Wygrywa";
	}
	else
		return "Przegrywa";
}

void Czlowiek::rysowanie() {
	cout << '@';
}

string Czlowiek::getNazwa() {
	return "czlowiek";
}

void Czlowiek::setKierunek(pair<int, int> kierunek) {
	Czlowiek::kierunek = kierunek;
}

pair<int, int> Czlowiek::getKierunek() {
	return Czlowiek::kierunek;
}

void Czlowiek::setCzasUmiejetnosci(int czasUmiejetnosci) {
	Czlowiek::czasUmiejetnosci = czasUmiejetnosci;
}

int Czlowiek::getCzasUmiejetnosci() {
	return Czlowiek::czasUmiejetnosci;
}

bool Czlowiek::isAktywacja() {
	return Czlowiek::aktywacja;
}

void Czlowiek::setAktywacja(bool aktywacja) {
	Czlowiek::aktywacja = aktywacja;
}