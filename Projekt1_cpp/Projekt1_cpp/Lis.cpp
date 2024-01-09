#include "Lis.h"

Lis::Lis(pair<int, int>pole, Swiat* swiat)
	:Zwierze(3, 7, 0, pole, swiat) {}

Organizm* Lis::potomek(pair<int, int>pole) {
	return new Lis(pole, swiat);
}

void Lis::rysowanie() {
	cout << 'l';
}

pair<int, int> Lis::losujPole(bool czyWolne) {
	vector<pair<int, int> >sasiednie;
	if (pole.first != 0) {
		if (swiat->getSwiat()[pole.first - 1][pole.second]->getSila() <= sila) {
			sasiednie.push_back(make_pair(pole.first - 1, pole.second));
		}
	}
	if (pole.first != swiat->getX() - 1) {
		if (swiat->getSwiat()[pole.first + 1][pole.second]->getSila() <= sila) {
			sasiednie.push_back(make_pair(pole.first + 1, pole.second));
		}
	}
	if (pole.second != swiat->getY() - 1) {
		if (swiat->getSwiat()[pole.first][pole.second + 1]->getSila() <= sila) {
			sasiednie.push_back(make_pair(pole.first, pole.second + 1));
		}
	}
	if (pole.second != 0) {
		if (swiat->getSwiat()[pole.first][pole.second - 1]->getSila() <= sila) {
			sasiednie.push_back(make_pair(pole.first, pole.second - 1));
		}
	}
	if (sasiednie.size() == 0)
		return make_pair(-1, -1);
	return sasiednie[rand() % sasiednie.size()];
}

string Lis::getNazwa() {
	return "lis";
}