package Sklep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Magazyn {
    private List<Artykul> artykuly= new ArrayList<>();
    public Magazyn() {
        try(BufferedReader br = new BufferedReader(new FileReader("Artykuly.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] informacje = line.split(";");
                artykuly.add(new Artykul(informacje[0], informacje[1], Float.parseFloat(informacje[2])));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public Artykul dodajArtykul(Artykul a) {
        artykuly.add(a);
        return a;
    }
    public Artykul pobierzArtykul(Artykul a) {
        artykuly.remove(a);
        return a;
    }
    public Optional<Artykul> znajdzArtykul(List<Artykul> artykuly, String artykul) throws NieprawidlowyArtykulException{
        String szukane = artykul.replace("*", ".*").replace("?", ".{1}");
        Optional<Artykul> found= artykuly.stream()
                .filter(a -> a.getNazwa().matches(szukane))
                .findFirst();
        if(found.isPresent()) {
            return found;
        } else {
            throw new NieprawidlowyArtykulException();
        }
    }
    public Optional<Artykul> znajdzArtykul(String artykul) throws NieprawidlowyArtykulException {
        String szukane = artykul.replace("*", ".*").replace("?", ".{1}");
        Optional<Artykul> found= this.artykuly.stream()
                .filter(a -> a.getNazwa().matches(szukane))
                .findFirst();
        if(found.isPresent()) {
            return found;
        } else {
            throw new NieprawidlowyArtykulException();
        }
    }
    public void wyswietlArtykuly() {
        for(Artykul a : artykuly) {
            System.out.println(a.toString());
        }
    }
    public void zapisz() {
        try(PrintWriter pw = new PrintWriter(new FileWriter("Artykuly.txt"))) {
            for(Artykul a : artykuly) {
                pw.println(a.getKod() + ";" + a.getNazwa() + ";" + a.getCena());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
