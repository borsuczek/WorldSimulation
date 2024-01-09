#include "Wilk.h"

Wilk::Wilk(pair<int, int>pole, Swiat* swiat)
	:Zwierze(9, 5, 0, pole, swiat) {}

Organizm* Wilk::potomek(pair<int, int>pole) {
	return new Wilk(pole, swiat);
}

void Wilk::rysowanie() {
	cout << 'w';
}

string Wilk::getNazwa() {
	return "wilk";
}