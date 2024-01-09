#include "Swiat.h"
#include "PustePole.h"
#include "Czlowiek.h"
#include "Antylopa.h"
#include "Guarana.h"
#include "Lis.h"
#include "PustePole.h"
#include "Trawa.h"
#include "Wilk.h"
#include "Zolw.h"
#include "WilczeJagody.h"
#include "Owca.h"
#include "Mlecz.h"
#include "BarszczSosnowskiego.h"


Swiat::Swiat(const int x, const int y) {
	this->x = x;
	this->y = y;
	swiat = new Organizm * *[x];
	for (int i = 0; i < x; i++) {
		swiat[i] = new Organizm * [y];
		for (int j = 0; j < y; j++) {
			swiat[i][j] = new PustePole();
		}
	}
}

Swiat::Swiat(){
}

void Swiat::wykonajTure() {
	vector<Organizm*> organizmy;
	for (int i = 0; i < x; i++) {
		for (int j = 0; j < y; j++) {
			if (swiat[i][j]->getNazwa() != "puste") {
				swiat[i][j]->setWiek(swiat[i][j]->getWiek() + 1);
				organizmy.push_back(swiat[i][j]);
			}
		}
	}
	for (int i = 0; i < organizmy.size(); i++) {
		for (int j = 0; j < organizmy.size() - 1; j++) {
			if (!komparator(organizmy[j], organizmy[j + 1])) {
				swap(organizmy[j], organizmy[j + 1]);
			}
		}
	}
	if (Czlowiek::isAktywacja() == true) {
		if (Czlowiek::getCzasUmiejetnosci() == -5)
			Czlowiek::setCzasUmiejetnosci(5);
	}
	for (int i = 0; i < organizmy.size(); i++) {
		if (organizmy[i]->isZyje() == true)
			organizmy[i]->akcja();
	}
}

bool Swiat::komparator(Organizm* a, Organizm* b) {
	if (a->getIncjatywa() > b->getIncjatywa()) {
		return true;
	}
	else if (a->getIncjatywa() == b->getIncjatywa()) {
		if (a->getWiek() >= b->getWiek())
			return true;
	}
	return false;
}

void Swiat::rysujSwiat() {
	cout << endl;
	for (int i = 0; i < y; i++) {
		for (int j = 0; j < x; j++) {
			swiat[j][i]->rysowanie();
		}
		cout << endl;
	}
	cout << endl;
}

void Swiat::rysujNaglowek() {
	cout << "Aleksandra Borsuk 184620" << endl << endl;
	cout << "Czlowiek porusza sie za pomoca strzalek" << endl;
	cout << "Mozna przejsc do nastepnej tury nie przemieszczajac czlowieka, a uzywajac klawisza enter" << endl;
	cout << "Aktywacja specjalnej umiejetnosci czlowieka nastepuje poprzez uzycie klawisza u" << endl;
	cout << "Nacisnij klawisz z aby zapisac i opuscic swiat" << endl;
	cout << endl;
}

void Swiat::generujSwiat() {
	pair<int, int> czlowiek = make_pair(rand() % x, rand() % y);
	swiat[czlowiek.first][czlowiek.second] = new Czlowiek(czlowiek, this);
	int rozmiarPlanszy = x * y;
	for (int j = 0; j < ILOSC_ORGANIZMOW; j++) {
		int i = 0;
		int iloscOrganizmu = rand() % (rozmiarPlanszy / ILOSC_ORGANIZMOW) + 1;
		while (i < iloscOrganizmu) {
			pair<int, int> organizm = make_pair(rand() % x, rand() % y);
			if (swiat[organizm.first][organizm.second]->getNazwa() == "puste") {
				if (j == 0)
					swiat[organizm.first][organizm.second] = new Owca(organizm, this);
				else if (j == 1)
					swiat[organizm.first][organizm.second] = new Wilk(organizm, this);
				else if (j == 2)
					swiat[organizm.first][organizm.second] = new Lis(organizm, this);
				else if (j == 3)
					swiat[organizm.first][organizm.second] = new Zolw(organizm, this);
				else if (j == 4)
					swiat[organizm.first][organizm.second] = new Antylopa(organizm, this);
				else if (j == 5)
					swiat[organizm.first][organizm.second] = new Trawa(organizm, this);
				else if (j == 6)
					swiat[organizm.first][organizm.second] = new Mlecz(organizm, this);
				else if (j == 7)
					swiat[organizm.first][organizm.second] = new Guarana(organizm, this);
				else if (j == 8)
					swiat[organizm.first][organizm.second] = new WilczeJagody(organizm, this);
				else if (j == 9)
					swiat[organizm.first][organizm.second] = new BarszczSosnowskiego(organizm, this);
				i++;
			}
		}
	}
	rysujNaglowek();
}

void Swiat::zapiszSwiat() {
	ofstream zapis("zapis.txt");
	zapis << x << " " << y << endl;
	for (int i = 0; i < y; i++) {
		for (int j = 0; j < x; j++) {
			if (swiat[i][j]->getNazwa() != "puste") {
				string nazwa = swiat[i][j]->getNazwa();
				nazwa.erase(remove(nazwa.begin(), nazwa.end(), ' '));
				zapis << nazwa << endl;
				zapis << swiat[i][j]->getSila() << " " << swiat[i][j]->getWiek() << " " << swiat[i][j]->getPole().first << " " << swiat[i][j]->getPole().second << endl;
				if (nazwa == "czlowiek")
					zapis << Czlowiek::getCzasUmiejetnosci() << " " << Czlowiek::isAktywacja() << endl;

			}
		}
	}
	zapis.close();
}

void Swiat::wczytajSwiat() {
	ifstream zapis("zapis.txt");
	string organizm;
	int sila, wiek, pole1, pole2, a, b;
	zapis >> a >> b;
	x = a;
	y = b;
	swiat = new Organizm * *[x];
	for (int i = 0; i < x; i++) {
		swiat[i] = new Organizm * [y];
		for (int j = 0; j < y; j++) {
			swiat[i][j] = new PustePole();
		}
	}
	while (zapis >> organizm) {
		zapis >> sila >> wiek >> pole1 >> pole2;
		pair<int, int>pole = make_pair(pole1, pole2);
		if (organizm == "owca")
			swiat[pole1][pole2] = new Owca(pole, this);
		else if (organizm == "wilk")
			swiat[pole1][pole2] = new Wilk(pole, this);
		else if (organizm == "lis")
			swiat[pole1][pole2] = new Lis(pole, this);
		else if (organizm == "zolw")
			swiat[pole1][pole2] = new Zolw(pole, this);
		else if (organizm == "antylopa")
			swiat[pole1][pole2] = new Antylopa(pole, this);
		else if (organizm == "trawa")
			swiat[pole1][pole2] = new Trawa(pole, this);
		else if (organizm == "mlecz")
			swiat[pole1][pole2] = new Mlecz(pole, this);
		else if (organizm == "guarana")
			swiat[pole1][pole2] = new Guarana(pole, this);
		else if (organizm == "wilczejagody")
			swiat[pole1][pole2] = new WilczeJagody(pole, this);
		else if (organizm == "barszczsosnowskiego")
			swiat[pole1][pole2] = new BarszczSosnowskiego(pole, this);
		else if (organizm == "czlowiek") {
			swiat[pole1][pole2] = new Czlowiek(pole, this);
			bool aktywacja; int czasUmiejetnosci;
			zapis >> czasUmiejetnosci >> aktywacja;
			Czlowiek::setAktywacja(aktywacja);
			Czlowiek::setCzasUmiejetnosci(czasUmiejetnosci);
		}
		swiat[pole1][pole2]->setSila(sila);
		swiat[pole1][pole2]->setWiek(wiek);
	}
	zapis.close();
	rysujNaglowek();
}

int Swiat::getX() {
	return x;
}
int Swiat::getY() {
	return y;
}

Organizm*** Swiat::getSwiat() {
	return swiat;
}