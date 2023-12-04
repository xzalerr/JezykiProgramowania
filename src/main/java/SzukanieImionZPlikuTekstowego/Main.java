package SzukanieImionZPlikuTekstowego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Imie>> litery = new ArrayList<>();
        for(int i = 0; i < Litery.values().length; i++) {
            litery.add(new ArrayList<>());
        }
        for(int i = 0; i < 3; i++) {

            try(BufferedReader br = new BufferedReader(new FileReader(args[i]));) {
                if(args.length != 3) {
                    throw new IndexOutOfBoundsException("Musisz podać dokładnie 3 pliki oddzielone od siebie spacją");
                }
                String line;
                while((line = br.readLine()) != null) {
                    String[] imiona = line.split(" ");
                    if(imiona.length == 0) continue;
                    for(String nazwa : imiona) {
                        List<Imie> lista = litery.get(Litery.valueOf(nazwa.substring(0, 1)).ordinal());
                        Imie imie = null;
                        for(Imie x : lista) {
                            if(x.getName().equals(nazwa)) {
                                imie = x;
                                break;
                            }
                        }
                        if(imie != null) {
                            imie.setCount(imie.getCount()+1);
                            if(i==0) imie.setCountOne(imie.getCountOne() + 1);
                            else if(i==1) imie.setCountTwo(imie.getCountTwo() + 1);
                            else if(i==2) imie.setCountThree(imie.getCountThree() + 1);
                        } else if(Imiona.contains(nazwa)) {
                            Imie pierwszyRaz = new Imie(nazwa);
                            lista.add(pierwszyRaz);
                            if(i==0) pierwszyRaz.setCountOne(pierwszyRaz.getCountOne() + 1);
                            else if(i==1) pierwszyRaz.setCountTwo(pierwszyRaz.getCountTwo() + 1);
                            else if(i==2) pierwszyRaz.setCountThree(pierwszyRaz.getCountThree() + 1);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Nie udało się znaleźć pliku o podanej nazwie.");
                e.printStackTrace();
            }
        }
        for(Litery l : Litery.values()) {
            System.out.println(l.toString());
            for(Imie imie : litery.get(l.ordinal())) {
                System.out.println(imie.getName() + ", łącznie: " + imie.getCount() + ", w pliku " + args[0] + ": " + imie.getCountOne()
                + ", w pliku " + args[1] + ": " + imie.getCountTwo() + ", w pliku: " + args[2] + ": " + imie.getCountThree());
            }
        }
    }
}
