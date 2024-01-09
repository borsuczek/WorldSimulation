#include "Trawa.h"

Trawa::Trawa(pair<int, int>pole, Swiat* swiat)
	:Roslina(0, 0, pole, swiat) {}

Organizm* Trawa::potomek(pair<int, int>pole) {
	return new Trawa(pole, swiat);
}

void Trawa::rysowanie() {
	cout << 't';
}

string Trawa::getNazwa() {
	return "trawa";
}