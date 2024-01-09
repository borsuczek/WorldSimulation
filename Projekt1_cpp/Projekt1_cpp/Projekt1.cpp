#include <iostream>
#include <conio.h>
#include <stdlib.h>
#include "Swiat.h"
#include "Czlowiek.h"
#include <time.h>

using namespace std;

int main()
{
    srand(time(NULL));
    cout << "Aby wczytac swiat nacisnij 1" << endl;
    cout << "Aby stworzyc nowy swiat nacisnij 2" << endl;
    int poczatek = _getch();
    system("cls");
    Swiat* swiat = new Swiat();
    if (poczatek == 49) {
        swiat->wczytajSwiat();
    }
    else if (poczatek == 50) {
        int x, y;
        cout << "Podaj wielkosc planszy:" << endl;
        cin >> x >> y;
        swiat = new Swiat(x, y);
        system("cls");
        swiat->generujSwiat();
    }
    
    swiat->rysujSwiat();
    while (true) {
        int ASCII = _getch();
        bool zmiana = false;
        if (ASCII == 'u') {
            Czlowiek::setAktywacja(true);
        }
        if (ASCII == 224) {
            int strzalka = _getch();
            if (strzalka == 72) {
                Czlowiek::setKierunek(make_pair(0, -1));
                zmiana = true;
            }
            else if (strzalka == 75) {
                Czlowiek::setKierunek(make_pair(-1, 0));
                zmiana = true;
            }
            else if (strzalka == 80) {
                Czlowiek::setKierunek(make_pair(0, 1));
                zmiana = true;
            }
            else if (strzalka == 77) {
                Czlowiek::setKierunek(make_pair(1, 0));
                zmiana = true;
            }
        }
        else if (ASCII == 13)
            zmiana = true;        
        if (ASCII == 'z') {
            swiat->zapiszSwiat();
            zmiana = false;
            exit(0);
        }
        if (zmiana == true) {
            system("cls");
            swiat->rysujNaglowek();
            swiat->wykonajTure();
            swiat->rysujSwiat();
        }
    }
}