package Sklep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Koszyk {
    private Map<Artykul, Integer> produktyWKoszyku;
    public Koszyk() {
        produktyWKoszyku = new HashMap<>();
    }
    public void dodajProdukt(Artykul a) {
        produktyWKoszyku.put(a, produktyWKoszyku.containsKey(a) ? produktyWKoszyku.get(a)+1 : 1);
    }
    public void usunProdukt(Artykul a, int ilosc) throws NieprawidlowyArtykulException, NieprawidlowaIloscException {
        if(produktyWKoszyku.containsKey(a)) {
            if(produktyWKoszyku.get(a) >= 1 && produktyWKoszyku.get(a) >= ilosc) {
                int obecna = produktyWKoszyku.get(a);
                produktyWKoszyku.put(a, obecna - ilosc);
            } else {
                throw new NieprawidlowaIloscException();
            }
        } else {
            throw new NieprawidlowyArtykulException();
        }
    }
    public float wycenaZamowienia(Map<Artykul, Integer> x) {
        return (float) x.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getCena() * entry.getValue())
                .sum();
    }
    public Map<Artykul, Integer> getMap() {
        return produktyWKoszyku;
    }
    public List<Artykul> listaProduktowWKoszyku() {
        return new ArrayList<>(produktyWKoszyku.keySet());
    }
    public int iloscProduktowWKoszyku(Artykul a) {
        return produktyWKoszyku.get(a);
    }
}
