#include "PustePole.h"

PustePole::PustePole()
	:Organizm(-1, -1, -1, false, make_pair(-1, -1), nullptr, false) {}

Organizm* PustePole::potomek(pair<int, int>pole) {
	return nullptr;
}

void PustePole::rysowanie() {
	cout << "_";
}

string PustePole::getNazwa() {
	return "puste";
}