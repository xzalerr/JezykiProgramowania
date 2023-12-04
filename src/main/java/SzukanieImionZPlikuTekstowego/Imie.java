package SzukanieImionZPlikuTekstowego;

public class Imie {
    private String name;
    private int count;
    public int countOne = 0;
    public int countTwo = 0;
    public int countThree = 0;
    public Imie(String name) {
        this.name = name;
        this.count = 1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCountOne() {
        return countOne;
    }

    public void setCountOne(int countOne) {
        this.countOne = countOne;
    }

    public int getCountTwo() {
        return countTwo;
    }

    public void setCountTwo(int countTwo) {
        this.countTwo = countTwo;
    }

    public int getCountThree() {
        return countThree;
    }

    public void setCountThree(int countThree) {
        this.countThree = countThree;
    }
}
