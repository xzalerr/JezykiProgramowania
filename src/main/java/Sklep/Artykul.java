package Sklep;

import java.util.Objects;

public class Artykul {
    private String kod;
    private String nazwa;
    private float cena;
    public Artykul(String kod, String nazwa, float cena) {
        this.kod = kod;
        this.nazwa = nazwa;
        this.cena = cena;
    }
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }
    public String toString() {
        return "kod: " + kod + " nazwa: " + nazwa + " cena: " + cena + " ";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artykul artykul = (Artykul) o;
        return Objects.equals(nazwa, artykul.nazwa) &&
                Objects.equals(kod, artykul.kod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, kod);
    }
}
