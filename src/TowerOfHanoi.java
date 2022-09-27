public class TowerOfHanoi {

    public static void main(String[] args){

        System.out.println("Tower of Hanoi!");
        towerOfHanoi('A', 'B', 'C', 5);
    }

    public static void towerOfHanoi(char a, char b, char c, int brikkenr){

        // Basistilfelle
        if (brikkenr == 0) return;
        // Flytt alle unntatt nederste fra a til b
        towerOfHanoi(a, c, b, brikkenr-1);
        // Flytt nederste fra a til c
        System.out.println("Flytter brikke " + brikkenr +
                " fra " + a + " til " + c);
        // Flytt alle fra b til a
        towerOfHanoi(b, a, c, brikkenr-1);

    }

}
