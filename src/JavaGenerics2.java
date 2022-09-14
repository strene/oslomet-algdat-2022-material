import java.util.Arrays;

public class JavaGenerics2 {

    public static void main(String[] args){

        // Array av Pokemon
        Pokemon[] pokemons = {
                new Pokemon("Blastoise", 10, 1000),
                new Pokemon("Pikachu", 8, 500),
                new Pokemon("Charmander", 7, 120),
                new Pokemon("Gengar", 10, 1001),
                new Pokemon("Jigglypuff", 10, 500),
                new Pokemon("Blastoise", 10, 1002)
        };
        for (Pokemon p : pokemons){
            System.out.println(p);
        }
        System.out.println();

        // Lag comparator-objekt for synkende sammenligning
        DescendingPokemonComparator cmpD = new DescendingPokemonComparator();
        System.out.println(maks(pokemons, cmpD));

        // Sorter med komparatoren
        sorter(pokemons, cmpD);
        for (Pokemon p: pokemons) {
            System.out.println(p);
        }
        System.out.println();

        // Lag comparator-objekt for stigende sammenligning og sorter med mer komparatoren
        AscendingPokemonComparator cmpA = new AscendingPokemonComparator();
        sorter(pokemons, cmpA);
        for (Pokemon p: pokemons) {
            System.out.println(p);
        }
        System.out.println();

        // Test oddetall/partall-sorteringen
        Integer[] a = {1,5,6,7,9,2,4,5,2,5,6,12};
        OddePartallKomparator cmpOP = new OddePartallKomparator();
        sorter(a, cmpOP);
        System.out.println(Arrays.toString(a));

    }

    public static <T> int maks(T[] x, int fra, int til, Komparator<T> cmp){
        // Maks-funksjon for intervallet [fra, til) med komparator
        int mi = fra;
        T mv = x[mi];
        for (int i = fra; i < til; i++){
            if (cmp.compare(x[i], mv) > 0){
                mi = i;
                mv = x[mi];
            }
        }
        return mi;
    }

    public static <T> int maks(T[] x, Komparator<T> cmp){
        // Maksfunksjon med komparator
        return maks(x, 0, x.length, cmp);
    }

    public static <T> void sorter(T[] x, Komparator<T> cmp){
        // Sortering med komparator
        for (int i = 0; i < x.length; i++){
            int mi = maks(x, i, x.length, cmp);
            T temp = x[i];
            x[i] = x[mi];
            x[mi] = temp;
        }
    }

    @FunctionalInterface
    public interface Komparator<T>{
        // Komparator interface
        int compare (T x, T y);
    }

    public static class DescendingPokemonComparator implements Komparator<Pokemon> {
        // Komparator klasse for synkende sortering
        public int compare(Pokemon p1, Pokemon p2) {
            // Funksjon som returnerer > 0 om p1 > p2, 0 om p1 == p2, og < 0 om p1 < p2
            int out = p1.level.compareTo(p2.level);
            if (out == 0) {
                out = p1.hp.compareTo(p2.hp);
            }
            if (out == 0) {
                out = p1.name.compareTo(p2.name);
            }
            return out;
        }
    }

    public static class AscendingPokemonComparator implements Komparator<Pokemon> {
        // Komparator klasse for stigende sortering
        public int compare(Pokemon p1, Pokemon p2) {
            // Funksjon som returnerer > 0 om p2 > p1, 0 om p2 == p1, og < 0 om p2 < p1
            int out = p2.level.compareTo(p1.level);
            if (out == 0) {
                out = p2.hp.compareTo(p1.hp);
            }
            if (out == 0) {
                out = p2.name.compareTo(p1.name);
            }
            return out;
        }
    }

    public static class OddePartallKomparator implements Komparator<Integer>{
        // Komparator-klasse for oddetall/partall-sortering
        public int compare(Integer m, Integer n){
            // Funksjon som evaluarer partall > oddetall, og deretter tallverdi
            if (m % 2 == 0 && n % 2 == 0) {
                // Begge er partall
                return m.compareTo(n);
            } else if (m % 2 != 0 && n % 2 != 0) {
                // Begge er oddetall
                return m.compareTo(n);
            } else if (m % 2 > n % 2) {
                // m er oddetall, n er partall
                return 1;
            } else if (n % 2 > m % 2) {
                // n er oddetall, m er partall
                return -1;
            }
            return 0;
        }
    }

    public static class Pokemon {
        // Samme klasse som i JavaGenerics1, men uten compareTo
        String name;
        Integer level;
        Integer hp;

        Pokemon(String name, int level, int hp){
            // Konstruktor
            this.name = name;
            this.level = level;
            this.hp = hp;
        }

        public String toString(){
            // Denne funksjonen sørger for at System.out.print skriver
            // output som er lett å lese for mennesker
            return this.name + " (" + this.level + "," + this.hp + ")";
        }

    }

}