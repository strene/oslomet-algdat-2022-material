import javax.sound.sampled.Port;
import java.awt.datatransfer.FlavorEvent;

public class JavaGenerics1 {

    public static void main(String[] args){

        // Intervall-sjekking med og uten ternary if
        for (int x = -10; x <= 10; x++){
            System.out.println(x + " " + simpleIntervalCheck(x));
        }
        for (int x = -10; x <= 10; x++){
            System.out.println(x + " " + ternaryIntervalCheck(x));
        }
        System.out.println();

        // Indeks til største element

        // En maks-funksjon for hver datatype
        int[] v = {2,4,10,2,3,99,4,0};
        System.out.println(maksInt(v));
        char[] c = {'A','F','H','Z','L','P'};
        System.out.println(maksChar(c));
        System.out.println();

        // En generisk maks-funsksjon som støtter alle objekter som implemeterer Comparable
        Integer[] v2 = {2,4,10,2,3,99,4,0};
        System.out.println(maks(v2));
        Character[] c2 = {'A','F','H','Z','L','P'};
        System.out.println(maks(c2));
        String[] s = {"Algdat", "Er", "Kjempe", "Gøy", "!"};
        System.out.println(maks(s));
        System.out.println();

        // Klasse Pokemon som implementerer Comparable
        Pokemon[] pokemons = {
                new Pokemon("Blastoise", 10, 1000),
                new Pokemon("Pikachu", 8, 500),
                new Pokemon("Charmander", 7, 120),
                new Pokemon("Gengar", 10, 1001),
                new Pokemon("Jigglypuff", 10, 500),
                new Pokemon("Blastoise", 10, 1002)
        };
        // Test at toString funker som den skal
        System.out.println("Gotta catch 'em all!");
        for( Pokemon p: pokemons){
            System.out.println(p);
        }
        // Test at den generiske maks-funksjonen funker for for Pokemon
        System.out.println(pokemons[maks(pokemons)] + " jeg velger deg!");

    }

    public static int simpleIntervalCheck(int x){
        // Returner -1 hvis x < -5, 0 hvis -5 <= x <= 5, og 1 hvis x > 5
        int out = 0;
        if (x < -5){
            out--;
        }
        else if (x > 5){
            out++;
        }
        return out;
    }

    public static int ternaryIntervalCheck(int x){
        // Samme som simpleIntervalCheck. Men denne koden er lite lesbar!
        return (x < -5) ? -1 : ((x > 5) ? 1 : 0);
    }

    public static int maksInt(int[] x){
        // Returner indeks til største verdi for int[]
        int mi = 0;
        int mv = x[mi];
        for (int i = 1; i < x.length; i++){
            if (x[i] > mv){
                mi = i;
                mv = x[mi];
            }
        }
        return mi;
    }

    public static int maksChar(char[] c){
        // Returner indeks til største verdi for char[]
        int mi = 0;
        char mv = c[mi];
        for (int i = 1; i < c.length; i++){
            if (c[i] > mv){
                mi = i;
                mv = c[mi];
            }
        }
        return mi;
    }

    public static <T extends Comparable<? super T>> int maks(T[] x){
        // Returner indeks til største verdi for alle datatyper som er Comparable
        int mi = 0;
        T mv = x[mi];
        for (int i = 1; i < x.length; i++){
            if (x[i].compareTo(mv) > 0){
                mi = i;
                mv = x[mi];
            }
        }
        return mi;
    }

    public static class Pokemon implements Comparable<Pokemon>{
        // Klasse for Pokemons
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
            return name + " (" + level + "," + hp + ")";
        }

        public int compareTo(Pokemon otherPokemon){
            // Funksjon som definerer hvordan vi sammenligner to instanser av klassen
            // 1: Sammenlign level
            int out = this.level.compareTo(otherPokemon.level);
            if (out == 0){
                // 2: Samme level - sammenlign hp
                out = this.hp.compareTo(otherPokemon.hp);
            }
            if (out == 0){
                // 3: Samme hp og level - sammenlign navn
                out = this.name.compareTo(otherPokemon.name);
            }
            return out;
        }

    }

}
