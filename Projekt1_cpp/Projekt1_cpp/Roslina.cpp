#include "Roslina.h"

Roslina::Roslina(int sila, int wiek, pair<int, int>pole, Swiat* swiat)
	:Organizm(sila, 0, wiek, false, pole, swiat, true) {}

void Roslina::akcja() {
	rozprzestrzenianie();
}

void Roslina::rozprzestrzenianie() {
	if (czySieje() == true) {
		pair<int, int> nowaRoslina = losujPole(true);
		if (nowaRoslina.first != -1) {
			cout << getNazwa() << " rozprzestrzenil(a) sie na pole ( " << nowaRoslina.first + 1 << "," << nowaRoslina.second + 1 << " )" << endl;
			swiat->getSwiat()[nowaRoslina.first][nowaRoslina.second] = potomek(nowaRoslina);
		}
	}
}

bool Roslina::czySieje() {
	int los = rand() % 100;
	if (los <= 50)
		return true;
	return false;
}