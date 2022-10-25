import javax.naming.BinaryRefAddr;
import java.util.ArrayDeque;

public class BinaryTree {

    Node root; // Treets rotnode
    static class Node {
        // Klasse for noder i treet

        char value; // Nodens verdi
        Node leftChild; // Nodens venstre barn
        Node rightChild; // Nodens høyre barn
        Node parent; // Nodens forelder

        Node(char value){
            // Konstruktør som kun tar inn verdi
            this.value = value;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        Node(Node parent, char value){
            // Konstruktør som kun tar inn forelder og verdi
            this.value = value;
            this.parent = parent;
            this.leftChild = null;
            this.rightChild = null;
        }

        Node addLeftChild(char value){
            // Legg til venstre barn for en node
            this.leftChild = new Node(this, value);
            return this.leftChild;
        }

        Node addRightChild(char value){
            // Legg til høyre barn for en node
            this.rightChild = new Node(this, value);
            return this.rightChild;
        }

    }

    BinaryTree(){
        // Basiskonstruktør
        root = null;
    }

    BinaryTree(char[] values){
        // Konstruktør som bygger treet ved å legge inn nodene fra values i
        // inorden
        // Sett roten
        this.root = new Node(null, values[0]);
        // Lag kø og legg in rotnode
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);

        int i = 1;
        while (i < values.length){
            // Ta ut første element i køen
            Node current = queue.removeFirst();
            // Legg til neste verdi i values i nodens venstre barn
            current.addLeftChild(values[i]);
            // Legg den nye noden bakerst i køen
            queue.addLast(current.leftChild);
            if (i < values.length - 1){
                // Det er flere elementer i values
                // Legg til neste verdi i values i nodens høyre barn
                current.addRightChild(values[i+1]);
                // Legg den nye noden bakerst i køen
                queue.addLast(current.rightChild);
            }
            // Inkrementer i med to (vi har lagt til i og evt i+1)
            i += 2;
        }
    }

    void insert(char value){
        // Setter inn verdi ved å starte i rot

        // Sjekk om treet har en rot, og lag rot hvis ikke
        if (root == null) {root = new Node(value);}
        // Sett inn verdi
        else {insert(root, value);}

    }

    static void insert(Node current, char value){
        // Setter inn value under current
        if (value < current.value){
            // value er mindre - gå til venstre
            if (current.leftChild == null) {
                // Basistilfelle - legg til node
                current.leftChild = new Node(current, value);
            }
            else {
                // Rekursivt kall med venstre barn
                insert(current.leftChild, value);
            }
        }
        else {
            // value er større enn eller lik - gå til høyre
            if (current.rightChild == null) {
                // Basistilfelle - legg til node
                current.rightChild = new Node(current, value);
            }
            else {
                // Rekursivt kall med høyre barn
                insert(current.rightChild, value);
            }
        }

    }

    static void printBreadthFirst(Node root){
        // Skriver ut verdiene i nivåorden (bredde først)

        // Hjelpekø
        ArrayDeque<Node> queue = new ArrayDeque<>();
        // Legg til rot bakerst
        queue.addLast(root);

        while (!queue.isEmpty()){
            // Ta ut første element
            Node current = queue.removeFirst();
            if (current.leftChild != null){
                // Leggt il venstre barn bakerst i køen
                queue.addLast(current.leftChild);
            }
            if (current.rightChild != null){
                // Legg til høyre barn bakers i køen
                queue.addLast(current.rightChild);
            }
            // Skri ut verdi
            System.out.print(current.value + " ");
        }
    }

    void printDepthFirst(int order){
        // Kaller printDepthFirst med rotnoden som start
        printDepthFirst(root, order);
    }

    static void printDepthFirst(Node node, int order){
        // Skriver ut dybde først fra node i preorder (order = 1), inorden
        // (orden = 2), eller postorden (order = 3)

        if (node == null){
            // Basistilfelle
            return;
        }
        // Preorden
        if (order == 1) System.out.print(node.value + " ");
        // Rekursivt kall med venstre barn
        printDepthFirst(node.leftChild, order);
        // Inorden
        if (order == 2) System.out.print(node.value + " ");
        // Rekursivt kall med høyre barn
        printDepthFirst(node.rightChild, order);
        // Postorden
        if (order == 3) System.out.print(node.value + " ");

    }

    static void printDepthFirstIterative(Node root){
        // Skriver ut dybde først iterativt (preorden)

        // Hjelpestakk
        ArrayDeque<Node> stack = new ArrayDeque<>();
        // Legg inn roten først
        stack.push(root);

        while(!stack.isEmpty()){
            // Ta ut første element
            Node current = stack.pop();
            if (current.rightChild != null){
                // Legg til høyre barn øverst i stakken
                stack.push(current.rightChild);
            }
            if (current.leftChild != null){
                // Legg til venstre barn i stakken
                stack.push(current.leftChild);
            }
            // Skriv ut nodens verdi
            System.out.print(current.value + " ");
        }
        // Avslutt med linjeskift
        System.out.println();

    }

    static Node nextPreorder(Node p){
        // Finner neste node i preorden etter p

        if (p.leftChild != null) {
            // p har ventre barn - det er neste i preorden
            return p.leftChild;
        }
        else if (p.rightChild != null){
            // p har ikke ventre barn, men høyre barn - det er neste i preorden
            return p.rightChild;
        }
        else {
            // p har ingen barn - traverser oppover til p er venstre barn og
            // parent har et høyre barn, eller parent == null
            Node parent = p.parent;
            while (parent != null &&
                    (parent.rightChild == null || parent.rightChild == p)){
                parent = parent.parent;
                p = p.parent;
            }
            if (parent != null) {
                // p er venstre barn - parent sitt høyre barn er neste i preorden
                return parent.rightChild;
            }
            else {
                return null;
            }
        }

    }

    public static void main(String[] args){

        // Bygg binærtre ved å legge inn i nivåorden
        char[] values = "ABCDEFGHIJKLM".toCharArray();
        BinaryTree bt = new BinaryTree(values);

        // Skriv ut treet i nivåorden
        printBreadthFirst(bt.root);

        // Bygg binærtre ved å legge inn i nivåorden
        char[] values2 = "!GTALDA".toCharArray();
        BinaryTree bt2 = new BinaryTree(values2);
        // Skriv ut i nivåorden
        System.out.println();
        printBreadthFirst(bt2.root);
        // Skriv ut i preorden
        System.out.println();
        printDepthFirst(bt2.root,1);
        // Skriv ut i inorden
        System.out.println();
        printDepthFirst(bt2.root,2);
        // Skriv ut i postorden
        System.out.println();
        printDepthFirst(bt2.root,3);
        // Skriv ut i preorden iterativt
        System.out.println();
        printDepthFirstIterative(bt2.root);

        // Sett inn i binært søketre
        char[] algdat = "ALGDAT".toCharArray();
        BinaryTree sbt = new BinaryTree();
        for (char value : algdat){
            sbt.insert(value);
        }
        // Skriv ut i preorden
        System.out.println();
        sbt.printDepthFirst(1);

        // Burk nextPreorder til å skrive ut i preorden
        System.out.println();
        Node p = sbt.root;
        while (p != null){
            System.out.print(p.value + " ");
            p = nextPreorder(p);
        }

    }

}
