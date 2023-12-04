package Sklep;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Klient {
    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();
        Koszyk koszyk = new Koszyk();
        Scanner sc = new Scanner(System.in);
        magazyn.wyswietlArtykuly();
        int wybor = -1;
        while(wybor != 0) {
            System.out.println("1. Sprawdź czy dany artykuł jest dostępny.");
            System.out.println("2. Dodaj artykuł do koszyka.");
            System.out.println("3. Usuń artykuł(y) z koszyka");
            System.out.println("4. Wyceń koszyk.");
            System.out.println("5. Zrealizuj zamówienie.");
            System.out.println("0. Wyjdź.");
            try {
                wybor = sc.nextInt();
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.out.println("Podana wartość musi być liczbą całkowitą.");
                sc.nextLine();
            }
            if(wybor == 1) {
                System.out.println("Podaj nazwę artykułu:");
                String nazwa = sc.nextLine();
                try {
                    Optional<Artykul> a = magazyn.znajdzArtykul(nazwa);
                    System.out.println("Artykuł: " + a.get().getNazwa() + " jest dostępny.");
                } catch(NieprawidlowyArtykulException e) {
                    System.out.println(e.getMessage());
                }
            } else if(wybor == 2) {
                System.out.println("Podaj nazwę artykułu:");
                String nazwa = sc.nextLine();
                try {
                    Optional<Artykul> a = magazyn.znajdzArtykul(nazwa);
                    koszyk.dodajProdukt(magazyn.pobierzArtykul(a.get()));
                    System.out.println("Dodano do koszyka.");
                } catch(NieprawidlowyArtykulException e) {
                    System.out.println(e.getMessage());
                }
            } else if(wybor == 3) {
                System.out.println("Podaj nazwę artykułu:");
                String nazwa = sc.nextLine();
                int ilosc = 0;
                try {
                    Optional<Artykul> a = magazyn.znajdzArtykul(koszyk.listaProduktowWKoszyku(), nazwa);
                    System.out.println("Podaj wartość do usunięcia. Aktualna ilość w koszyku: " + koszyk.iloscProduktowWKoszyku(a.get()));
                    try {
                        ilosc = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Podana wartość musi być liczbą całkowitą.");
                        sc.nextLine();
                    }
                    try {
                        koszyk.usunProdukt(a.get(), ilosc);
                        for (int i = 0; i < ilosc; i++) {
                            magazyn.dodajArtykul(a.get());
                        }
                        System.out.println("Artykuł usunięty.");
                    } catch (NieprawidlowyArtykulException | NieprawidlowaIloscException e) {
                        System.out.println(e.getMessage());
                    }
                } catch(NieprawidlowyArtykulException e) {
                    System.out.println(e.getMessage());
                }
            } else if(wybor == 4) {
                System.out.println(koszyk.wycenaZamowienia(koszyk.getMap()));
            } else if(wybor == 5) {
                magazyn.zapisz();
                System.out.println("Zamowienie zostalo zrealizowane. Stan magazynu po zamówieniu:");
                magazyn.wyswietlArtykuly();
            } else if(wybor == 0) {
                System.out.println("Opuściłeś sklep.");
            } else {
                System.out.println("Nieprawidłowa wartość. Podaj prawidłową wartość.");
            }
        }
    }
}
