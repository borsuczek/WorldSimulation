#include "Mlecz.h"

Mlecz::Mlecz(pair<int, int>pole, Swiat* swiat)
	:Roslina(0, 0, pole, swiat) {}

Organizm* Mlecz::potomek(pair<int, int>pole) {
	return new Mlecz(pole, swiat);
}

void Mlecz::akcja() {
	rozprzestrzenianie();
	rozprzestrzenianie();
	rozprzestrzenianie();
}

void Mlecz::rysowanie() {
	cout << 'm';
}

string Mlecz::getNazwa() {
	return "mlecz";
}