package SzukanieImionZPlikuTekstowego;

public enum Imiona {
    Rafał,
    Robert,
    Dawid,
    Wojtek;
    public static boolean contains(String test) {
        for (Imiona i : Imiona.values()) {
            if (i.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
