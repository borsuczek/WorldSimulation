#include "Guarana.h"

Guarana::Guarana(pair<int, int>pole, Swiat* swiat)
	:Roslina(0, 0, pole, swiat) {}

Organizm* Guarana::potomek(pair<int, int>pole) {
	return new Guarana(pole, swiat);
}

void Guarana::rysowanie() {
	cout << 'g';
}

string Guarana::rodzajKolizji(Organizm* organizm) {
	organizm->setSila(organizm->getSila() + 3);
	return "Wygrywa";
}

string Guarana::getNazwa() {
	return "guarana";
}