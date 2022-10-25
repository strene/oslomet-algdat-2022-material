import javax.naming.BinaryRefAddr;
import java.util.ArrayDeque;

public class BinaryTree {

    Node root;
    static class Node {

        char value;
        Node leftChild;
        Node rightChild;
        Node parent;

        Node(char value){
            this.value = value;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        Node(Node parent, char value){
            this.value = value;
            this.parent = parent;
            this.leftChild = null;
            this.rightChild = null;
        }

        Node addLeftChild(char value){
            this.leftChild = new Node(this, value);
            return this.leftChild;
        }

        Node addRightChild(char value){
            this.rightChild = new Node(this, value);
            return this.rightChild;
        }

    }

    BinaryTree(){
        root = null;
    }

    BinaryTree(char[] values){

        this.root = new Node(null, values[0]);
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);

        int i = 1;
        while (i < values.length){
            Node current = queue.removeFirst();
            current.addLeftChild(values[i]);
            queue.addLast(current.leftChild);
            if (i < values.length - 1){
                current.addRightChild(values[i+1]);
                queue.addLast(current.rightChild);
            }
            i += 2;
        }
    }

    void insert(char value){

        if (root == null) {root = new Node(value);}
        else {insert(root, value);}

    }

    static void insert(Node current, char value){

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
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()){
            Node current = queue.removeFirst();
            if (current.leftChild != null){
                queue.addLast(current.leftChild);
            }
            if (current.rightChild != null){
                queue.addLast(current.rightChild);
            }
            System.out.print(current.value + " ");
        }
    }

    void printDepthFirst(int order){
        printDepthFirst(root, order);
    }

    static void printDepthFirst(Node node, int order){

        if (node == null){
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

        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node current = stack.pop();
            if (current.rightChild != null){
                stack.push(current.rightChild);
            }
            if (current.leftChild != null){
                stack.push(current.leftChild);
            }
            System.out.print(current.value + " ");
        }
        System.out.println();

    }

    static Node nextPreorder(Node p){

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

        char[] values = "ABCDEFGHIJKLM".toCharArray();
        BinaryTree bt = new BinaryTree(values);

        printBreadthFirst(bt.root);

        char[] values2 = "!GTALDA".toCharArray();
        BinaryTree bt2 = new BinaryTree(values2);

        printBreadthFirst(bt2.root);
        System.out.println();
        printDepthFirst(bt2.root,1);
        System.out.println();
        printDepthFirst(bt2.root,2);
        System.out.println();
        printDepthFirst(bt2.root,3);
        System.out.println();
        printDepthFirstIterative(bt2.root);

        System.out.println();

        char[] algdat = "ALZGDXYAT".toCharArray();

        BinaryTree sbt = new BinaryTree();
        for (char value : algdat){
            sbt.insert(value);
        }
        sbt.printDepthFirst(1);

        System.out.println();
        Node p = sbt.root;
        while (p != null){
            System.out.print(p.value + " ");
            p = nextPreorder(p);
        }


    }

}
