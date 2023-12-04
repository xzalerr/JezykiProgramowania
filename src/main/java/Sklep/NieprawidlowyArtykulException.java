package Sklep;

public class NieprawidlowyArtykulException extends Exception{
    public NieprawidlowyArtykulException() {
        super("Podałeś nieprawidłowy artykuł, gdyż taki artykuł jest niedostępny.");
    }
}
