package Sklep;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Pracownik {
    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();
        Koszyk koszyk = new Koszyk();
        Scanner sc = new Scanner(System.in);
        magazyn.wyswietlArtykuly();
        int wybor = -1;
        while(wybor != 0) {
            System.out.println("1. Wyświetl dostępne artykuły.");
            System.out.println("2. Dodaj artykuł do magazynu.");
            System.out.println("3. Usuń artykuł z magazynu.");
            System.out.println("0. Wyjdź.");
            try {
                wybor = sc.nextInt();
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.out.println("Podana wartość musi być liczbą całkowitą.");
                sc.nextLine();
            }
            if(wybor == 1) {
                magazyn.wyswietlArtykuly();
            } else if(wybor == 2) {
                System.out.println("Podaj kod artykułu:");
                String kod = sc.nextLine();
                System.out.println("Podaj nazwę artykułu:");
                String nazwa = sc.nextLine();
                System.out.println("Podaj cenę artykułu:");
                float cena = 0;
                try {
                    cena = sc.nextFloat();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Podana wartość musi być liczbą zmienoprzecinkową.");
                    sc.nextLine();
                }
                magazyn.dodajArtykul(new Artykul(kod, nazwa, cena));
                magazyn.zapisz();
                System.out.println("Artykuł został dodany.");
            } else if(wybor == 3) {
                System.out.println("Podaj nazwę artykułu:");
                String nazwa = sc.nextLine();
                int ilosc = 0;
                try {
                    Optional<Artykul> a = magazyn.znajdzArtykul(nazwa);
                    magazyn.pobierzArtykul(a.get());
                } catch(NieprawidlowyArtykulException e) {
                    System.out.println(e.getMessage());
                }
                magazyn.zapisz();
                System.out.println("Artykuł został usunięty.");
            } else if(wybor == 0) {
                System.out.println("Opuściłeś magazyn.");
            } else {
                System.out.println("Nieprawidłowa wartość. Podaj prawidłową wartość.");
            }
        }
    }
}
