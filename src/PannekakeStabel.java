import java.util.LinkedList;

public class PannekakeStabel {

    public static void main(String[] args){

        spisPannekaker(3);
        System.out.println("Takk for maten!");
        System.out.println("Vasker opp main-tallerkenen");

    }

    public static void spisPannekaker(int n){

        System.out.println("Steker pannekake " + n + " og legger på main-tallerkenen");
        if (n > 1) {spisPannekaker(n-1);}
        System.out.println("Pannekake " + n + " var god!");

    }

}
