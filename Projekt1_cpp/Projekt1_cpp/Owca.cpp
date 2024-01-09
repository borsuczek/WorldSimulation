#include "Owca.h"

Owca::Owca(pair<int, int> pole, Swiat* swiat)
	:Zwierze(4, 4, 0, pole, swiat) {}

Organizm* Owca::potomek(pair<int, int>pole) {
	return new Owca(pole, swiat);
}

void Owca::rysowanie() {
	cout << 'o';
}

string Owca::getNazwa() {
	return "owca";
}