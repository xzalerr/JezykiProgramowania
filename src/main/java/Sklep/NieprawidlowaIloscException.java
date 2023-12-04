package Sklep;

public class NieprawidlowaIloscException extends Exception{
    public NieprawidlowaIloscException() {
        super("Podano zbyt dużą ilość artykułów do usunięcia w koszuka, w koszyku znajduje się mniej tego artykułu.");
    }
}
