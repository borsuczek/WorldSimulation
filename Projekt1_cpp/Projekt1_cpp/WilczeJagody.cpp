#include "WilczeJagody.h"

WilczeJagody::WilczeJagody(pair<int, int>pole, Swiat* swiat)
	:Roslina(99, 0, pole, swiat) {}

Organizm* WilczeJagody::potomek(pair<int, int>pole) {
	return new WilczeJagody(pole, swiat);
}

void WilczeJagody::rysowanie() {
	cout << 'j';
}

string WilczeJagody::rodzajKolizji(Organizm* organizm) {
	if (organizm->getSila() >= getSila()) {
		cout << getNazwa() << " ( " << getPole().first + 1 << "," << getPole().second + 1 << " ) zostaje zjedzony(a) przez " << organizm->getNazwa() << " ( " << organizm->getPole().first + 1 << "," << organizm->getPole().second + 1 << " ) ";
		swiat->getSwiat()[pole.first][pole.second]->setZyje(false);
		swiat->getSwiat()[pole.first][pole.second] = new PustePole();
	}
	return "Przegrywa";
}

string WilczeJagody::getNazwa() {
	return "wilcze jagody";
}