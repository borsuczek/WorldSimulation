#include "BarszczSosnowskiego.h"

BarszczSosnowskiego::BarszczSosnowskiego(pair<int, int>pole, Swiat* swiat)
	:Roslina(10, 0, pole, swiat) {}

Organizm* BarszczSosnowskiego::potomek(pair<int, int>pole) {
	return new BarszczSosnowskiego(pole, swiat);
}

void BarszczSosnowskiego::akcja() {
	zabija();
	rozprzestrzenianie();
}

void BarszczSosnowskiego::zabija() {
	if (pole.first != 0) {
		if (swiat->getSwiat()[pole.first - 1][pole.second]->isZwierze() == true) {
			cout << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " ) zabija zwierze " << swiat->getSwiat()[pole.first - 1][pole.second]->getNazwa() << " ( " << pole.first << "," << pole.second + 1 << " )" << endl;
			swiat->getSwiat()[pole.first - 1][pole.second]->setZyje(false);
			swiat->getSwiat()[pole.first - 1][pole.second] = new PustePole();
		}
	}
	if (pole.first != swiat->getX() - 1) {
		if (swiat->getSwiat()[pole.first + 1][pole.second]->isZwierze() == true) {
			cout << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " ) zabija zwierze " << swiat->getSwiat()[pole.first + 1][pole.second]->getNazwa() << " ( " << pole.first + 2 << "," << pole.second + 1 << " )" << endl;
			swiat->getSwiat()[pole.first + 1][pole.second]->setZyje(false);
			swiat->getSwiat()[pole.first + 1][pole.second] = new PustePole();
		}
	}
	if (pole.second != swiat->getY() - 1) {
		if (swiat->getSwiat()[pole.first][pole.second + 1]->isZwierze() == true) {
			cout << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " ) zabija zwierze " << swiat->getSwiat()[pole.first][pole.second + 1]->getNazwa() << " ( " << pole.first + 1 << "," << pole.second + 2 << " )" << endl;
			swiat->getSwiat()[pole.first][pole.second + 1]->setZyje(false);
			swiat->getSwiat()[pole.first][pole.second + 1] = new PustePole();
		}
	}
	if (pole.second != 0) {
		if (swiat->getSwiat()[pole.first][pole.second - 1]->isZwierze() == true) {
			cout << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " ) zabija zwierze " << swiat->getSwiat()[pole.first][pole.second - 1]->getNazwa() << " ( " << pole.first + 1 << "," << pole.second << " )" << endl;
			swiat->getSwiat()[pole.first][pole.second - 1]->setZyje(false);
			swiat->getSwiat()[pole.first][pole.second - 1] = new PustePole();
		}
	}
}

void BarszczSosnowskiego::rysowanie() {
	cout << 'b';
}

string BarszczSosnowskiego::rodzajKolizji(Organizm* organizm) {
	if (organizm->getSila() >= getSila()) {
		cout << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " ) zostaje zjedzony(a) przez " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " ) ";
		swiat->getSwiat()[pole.first][pole.second]->setZyje(false);
		swiat->getSwiat()[pole.first][pole.second] = new PustePole();
	}
	return "Przegrywa";
}

string BarszczSosnowskiego::getNazwa() {
	return "barszcz sosnowskiego";
}