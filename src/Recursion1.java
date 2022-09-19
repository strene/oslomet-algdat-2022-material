import jdk.jshell.EvalException;

import java.io.PrintStream;
import java.util.Arrays;

public class Recursion1 {

    public static void main(String[] args){

        System.out.println("Rekursjon!");
        System.out.println();

        // Tell ned fra 10 til 0 ved hjelp av rekursjon
        int n = 10;
        countdown(n);
        System.out.println();

        // Fakultet
        for (int k = 1; k <= 52; k++){
            System.out.println(k + "! = " + factorial(k));
        }

        // Rekursivt binærsøk
        int[] x = {0,2,12,21,32,36,37,99};
        System.out.println(Arrays.toString(x));
        // Sjekk at metoden finner indeksen til alle elementer i tabellen
        for (int xi : x){
            System.out.println(xi + " er i indeks " + binarySearch(x,xi));
        }
        // Sjekk hvordan metoden fungerer for verdier som ikke er i tabellen
        System.out.println(binarySearch(x, 25));

        // Rekursive permutasjoner
        int[] p0 = {1,2,3};
        permutation(p0);

        int[] p1 = {1,2,3,4,5,6};
        permutation(p1);

    }

    public static void countdown(int n){

        if (n == 0){
            System.out.println(n + " lift-off!");
            return;
        }
        System.out.println(n + "...");
        countdown(n-1);

    }

    public static int factorial(int n){

        if (n == 0) return 1;
        return n*factorial(n-1);

    }

    public static int binarySearch(int[] x, int value){
        return binarySearch(x, value, 0, x.length-1);
    }

    public static int binarySearch(int[] x, int value, int left, int right){

        if (left == right){
            // Bladnode i binærtreet - sjekk om vi har funnet veriden
            return (x[left] == value) ? left : -(left + 1);
        }
        int middle = (left + right)/2;
        if (x[middle] <= value){
            // Sjekk om vi har funnet verdien i en intern node
            if (x[middle] == value) return middle;
            // Søk i høyre gren
            return binarySearch(x, value, middle+1, right);
        }
        else {
            // Søk i venstre gren
            return binarySearch(x, value, left, middle-1);
        }

    }

    public static void permutation(int[] x){
        permutation(x, 0);
    }

    public static void permutation(int[] x, int k){

        if (k == x.length) System.out.println(Arrays.toString(x));

        for(int i = k; i < x.length; i++){
            swap(x, i, k);
            permutation(x, k+1);
            swap(x, i, k);
        }

    }

    public static void swap(int[] x, int i, int j){

        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;

    }

}
